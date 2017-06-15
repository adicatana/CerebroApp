/**
 * Created by ad5915 on 14/06/17.
 */


'use strict';

app.controller('MultiController', ['$scope', '$http', '$rootScope', 'currentUser', 'getGamePhase',
    function($scope, $http, $rootScope, currentUser, getGamePhase) {
        var vm = this;

        vm.stats = undefined;
        vm.match = undefined;
        vm.endMessage = "";
        vm.question = [];
        vm.returnMainScreen = returnMainScreen;

        vm.validateQuestion = validateQuestion;

        // Todo: CONNECTION STATUS with pings

        function returnMainScreen() {
            $http.get("/multi/end").then(function() {
                console.log("Return to main screen. ");
                getGamePhase();
            });
        }

        function setEndMessage() {
            $http.get("/multi/stats").then(function(response) {
                vm.stats = response.data;
                console.log(vm.stats);

                if (vm.stats.correct1 === vm.stats.correct2) {
                    vm.endMessage = "Tie!";
                    return;
                }
                if (vm.stats.player1.name === $rootScope.currentUser.name) {
                    if (vm.stats.correct1 > vm.stats.correct2) {
                        vm.endMessage = "You won!";
                    } else {
                        vm.endMessage = "You lost!";
                    }
                } else {
                    if (vm.stats.correct1 < vm.stats.correct2) {
                        vm.endMessage = "You won!";
                    } else {
                        vm.endMessage = "You lost!";
                    }
                }
            });
        }
        
        function getQuestion() {
            $http.get("/multi/random").then(function(response) {
                console.log("New question generated. ");
                vm.question = [response.data];
            }).then(function() {
                if (vm.question[0].answer === undefined) {
                    console.log("Game finished");
                    setEndMessage();
                    getGamePhase();
                }
            });
        }

        // Used so clicks responding to same question have no effect.
        var canValidate = true;

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

                }, 30000);

                // sync. players on next questions
                $http.get("/multi/next").then(function () {
                    updateStatsView();

                    getQuestion();
                    canValidate = true;
                });
            });
        }

        function updateStatsView() {
            $http.get("/multi/stats").then(function(response) {
                vm.stats = response.data;
                console.log(vm.stats);
            });
        }

        function questionFeedback(good, correct) {
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
        }

        init();
    }
]);