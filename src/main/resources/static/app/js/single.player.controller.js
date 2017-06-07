/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('SinglePlayerController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.getQuestion = randomQuestion;
            vm.validateQuestion = validateQuestion;
            vm.question = null;
            vm.percent = 0;

            function randomQuestion() {
                $http.get("/singleplayer/random").then(function (response) {
                    console.log("New question generated. ");
                    vm.question = [response.data];
                }).then(function () {
                    if (vm.question[0].answer === undefined) {
                        console.log("Game finished");

                        /* Call a public event. */
                        $scope.$emit('getGamePhase');
                    }
                });
            }

            function validateQuestion(answer) {
                $http.post("/singleplayer/answer", answer).then(function (response) {
                    console.log("You responded: " + answer);
                    var goodAnswer = response.data.answer;
                    console.log("Good answer: " + goodAnswer);

                    questionFeedback(goodAnswer === answer, goodAnswer);
                }).then(function () {
                    randomQuestion();
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

            init();

            function init() {
                randomQuestion();
            }
        }]);
})();
