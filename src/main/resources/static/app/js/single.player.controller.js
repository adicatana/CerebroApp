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
                }).then(function() {
                    randomQuestion();
                });
            }
        });


        var setTimer = function() {
            countdown.start();
            console.log('countdown360 ', countdown);
        };

        function randomQuestion() {
            $http.get("/singleplayer/random").then(function(response) {
                console.log("New question generated. ");
                vm.question = [response.data];
            }).then(function() {
                if (vm.question[0].answer === undefined) {
                    console.log("Game finished");
                    countdown.stop();

                    getGamePhase();
                } else {
                    setTimer();
                }
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
                randomQuestion();
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

        init();

        function init() {
            randomQuestion();
        }

    }
]);