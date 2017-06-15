/**
 * Created by andrei-octavian on 01.06.2017.
 */
'use strict';

app.controller('SinglePlayerController', ['$scope', '$http', '$rootScope', 'currentUser', 'getGamePhase',
    function($scope, $http, $rootScope, currentUser, getGamePhase) {
        var vm = this;

        vm.getQuestion = randomQuestion;
        vm.validateQuestion = validateQuestion;
        vm.question = null;
        vm.percent = 0;

        var countdown = $("#countdown").countdown360({
            radius: 60,
            strokeStyle: '#ff6a00',
            seconds: 15,
            fillStyle: "#e0d4cc",
            fontColor: '#000000',
            autostart: false,
            smooth: true,
            onComplete: function() {
                $http.post("/singleplayer/answer", " ").then(function(response) {
                    console.log("You responded: " + " ");
                    var goodAnswer = response.data.answer;
                    console.log("Good answer: " + goodAnswer);

                    questionFeedback(goodAnswer === " ", goodAnswer);
                });
            }
        });


        var setTimer = function() {
            console.log("I want to start the timer");
            countdown.start();
        };

        function randomQuestion() {
            $http.get("/singleplayer/random").then(function(response) {
                console.log("New question generated. ");
                vm.question = [response.data];
            }).then(function() {
                if (vm.question[0].answer === undefined) {
                    console.log("Game finished");
                    countdown.stop();
                    incrementGames();
                    getGamePhase();
                }
            });
        }

        function incrementGames() {
            $http.get("/singleplayer/gamecount").then(function() {
                console.log("No. of games a user played. ");
                currentUser();
            });
        }

        function validateQuestion(answer) {
            $http.post("/singleplayer/answer", answer).then(function(response) {
                console.log("You responded: " + answer);
                var goodAnswer = response.data.answer;
                console.log("Good answer: " + goodAnswer);

                questionFeedback(goodAnswer === answer, goodAnswer);
            }).then(function() {
                currentUser();
            });
        }

        function questionFeedback(good, correct) {
            countdown.stop();
            swal({
                title: good ? 'Good job!' : "Wrong!",
                text: good ? '' : "Correct answer: " + correct,
                type: good ? 'success' : 'error',
                timer: good ? 1000 : 2500,
                showConfirmButton: false,
                onClose: function () {
                    setTimer();
                    randomQuestion();
                }
            });
        }

        init();

        function init() {
            setTimer();
            randomQuestion();
        }

    }
]);