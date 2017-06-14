/**
 * Created by ad5915 on 14/06/17.
 */


'use strict';

app.controller('MultiController', ['$scope', '$http', '$rootScope', 'currentUser', 'getGamePhase',
    function($scope, $http, $rootScope, currentUser, getGamePhase) {
        var vm = this;

        vm.match = undefined;
        vm.question = [];

        vm.validateQuestion = validateQuestion;

        // Todo: CONNECTION STATUS with pings

        function getQuestion() {
            $http.get("/multi/random").then(function(response) {
                console.log("New question generated. ");
                vm.question = [response.data];
            }).then(function() {
                if (vm.question[0].answer === undefined) {
                    console.log("Game finished");
                    getGamePhase();
                }
            });
        }

        // needs to call getQuestion to generate a new question
        function validateQuestion(answer) {
            $http.post("/multi/answer", answer).then(function(response) {
                console.log("You responded: " + answer);
                var goodAnswer = response.data.answer;
                console.log("Good answer: " + goodAnswer);

                questionFeedback(goodAnswer === answer, goodAnswer);
            }).then(function() {
                currentUser();
                getQuestion();
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
            getQuestion();
        }

        init();
    }
]);