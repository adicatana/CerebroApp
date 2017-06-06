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
                        getPercentage();

                        /* Call a public event. */
                        $scope.$emit('getGamePhase');
                    }
                });
            }

            function validateQuestion(answer) {
                $http.get("/singleplayer/answer/" + answer).then(function (response) {
                    console.log("You responded: " + answer);
                    var goodAnswer = response.data.answer;
                    console.log("Good answer: " + goodAnswer);

                    questionFeedback(goodAnswer === answer, goodAnswer);
                }).then(function () {
                    randomQuestion();
                });
            }

            function getPercentage() {
                $http.get("/singleplayer/score/").then(function (response) {
                    vm.percent = response.data;
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

            function collectFeedback() {
                swal({
                        title: "Contribute",
                        text: "To contribute to our content, please add a question to be reponded by other players:",
                        type: "input",
                        showCancelButton: true,
                        closeOnConfirm: false,
                        animation: "slide-from-top",
                        inputPlaceholder: "Write something"
                    },
                    function(inputValue){
                        if (inputValue === false) return false;

                        if (inputValue === "") {
                            swal.showInputError("You need to write something!");
                            return false;
                        }

                        swal("Now please add a good answer to your question", "You wrote: " + inputValue, "success");
                        swal({
                                title: "Contribute: Answer",
                                text: "Now please add a good answer to your question:",
                                type: "input",
                                showCancelButton: true,
                                closeOnConfirm: false,
                                animation: "slide-from-top",
                                inputPlaceholder: "Write something"
                            },
                            function(inputValue){
                                if (inputValue === false) return false;

                                if (inputValue === "") {
                                    swal.showInputError("You need to write something!");
                                    return false
                                }
                                swal("Thank you for your input!", "" + inputValue, "success");
                            });
                    });
            }

            init();

            function init() {
                randomQuestion();
            }
        }]);
})();
