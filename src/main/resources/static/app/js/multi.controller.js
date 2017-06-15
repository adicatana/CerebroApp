'use strict';

app.controller('MultiController', ['$scope', '$http', '$rootScope', 'currentUser', 'getGamePhase',
    function($scope, $http, $rootScope, currentUser, getGamePhase) {

        $scope.stats = undefined;
        $scope.match = undefined;
        $scope.endMessage = "";
        $scope.question = [];
        $scope.returnMainScreen = returnMainScreen;

        $scope.validateQuestion = validateQuestion;



        // Used so clicks responding to same question have no effect.
        var canValidate = true;
        var connectionLost = true;

        var countdown = $("#countdown").countdown360({
            radius: 60,
            strokeStyle: '#ff6a00',
            seconds: 15,
            fillStyle: "#e0d4cc",
            fontColor: '#000000',
            autostart: false,
            smooth: true,
            onComplete: function() {
                $http.post("/multi/answer", " ").then(function(response) {
                    console.log("You responded: " + " ");
                    var goodAnswer = response.data.answer;
                    console.log("Good answer: " + goodAnswer);

                    questionFeedback(goodAnswer === " ", goodAnswer);
                }).then(function() {
                    $http.get("/multi/next").then(function () {
                        connectionLost = false;
                        updateStatsView();

                        getQuestion();
                        canValidate = true;
                    });
                });
            }
        });

        var setTimer = function() {
            countdown.start();
            console.log('countdown360 ', countdown);
        };

        // Todo: CONNECTION STATUS with pings

        function returnMainScreen() {
            $http.get("/multi/end").then(function() {
                console.log("Return to main screen. ");
                getGamePhase();
            });
        }

        function setEndMessage() {
            $http.get("/multi/stats").then(function(response) {
                $scope.stats = response.data;
                console.log($scope.stats);

                if ($scope.stats.correct1 === $scope.stats.correct2) {
                    $scope.endMessage = "Tie!";
                    return;
                }
                if ($scope.stats.player1.name === $rootScope.currentUser.name) {
                    if ($scope.stats.correct1 > $scope.stats.correct2) {
                        $scope.endMessage = "You won!";
                    } else {
                        $scope.endMessage = "You lost!";
                    }
                } else {
                    if ($scope.stats.correct1 < $scope.stats.correct2) {
                        $scope.endMessage = "You won!";
                    } else {
                        $scope.endMessage = "You lost!";
                    }
                }
            });
        }

        function getQuestion() {
            $http.get("/multi/random").then(function(response) {
                console.log("New question generated. ");
                $scope.question = [response.data];
            }).then(function() {
                if ($scope.question[0].answer === undefined) {
                    console.log("Game finished");
                    setEndMessage();
                    countdown.stop();
                    getGamePhase();
                } else {
                    setTimer();
                }
            });
        }

        // needs to call getQuestion to generate a new question
        function validateQuestion(answer) {
            if (!canValidate) {
                return;
            }

            $http.post("/multi/answer", answer).then(function(response) {
                console.log("You responded: " + answer);
                var goodAnswer = response.data.answer;
                console.log("Good answer: " + goodAnswer);

                updateStatsView();
                questionFeedback(goodAnswer === answer, goodAnswer);
            }).then(function() {
                currentUser();

                canValidate = false;

                setTimeout(function () {
                    if (connectionLost) {
                        $http.get("/multi/connection_lost").then(function () {
                            console.log("Connection with the other player has been lost.");
                            getGamePhase();

                            swal(
                                'Connection lost',
                                'Connection with the other player has been lost.',
                                'warning'
                            );
                        });
                    }
                }, 17000);

                // sync. players on next questions
                $http.get("/multi/next").then(function () {
                    connectionLost = false;
                    updateStatsView();

                    getQuestion();
                    canValidate = true;
                });
            });
        }

        function updateStatsView() {
            $http.get("/multi/stats").then(function(response) {
                $scope.stats = response.data;
                console.log($scope.stats);
                $scope.progressBarStyle1 = {
                    "width": $scope.stats.correct1 / $scope.totalQuestions * 100 + "%"
                };
                $scope.progressBarStyle2 = {
                    "width": $scope.stats.correct2 / $scope.totalQuestions * 100 + "%"
                };
            });
        }

        function questionFeedback(good, correct) {
            countdown.stop();
            swal({
                title: good ? 'Good job!' : "Wrong!",
                text: good ? '' : "Correct answer: " + correct,
                type: good ? 'success' : 'error',
                timer: good ? 800 : 2000,
                showConfirmButton: false
            });
        }

        function init() {
            updateStatsView();
            getQuestion();
            $scope.totalQuestions = 5;
        }

        init();
    }
]);