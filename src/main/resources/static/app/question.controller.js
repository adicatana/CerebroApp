/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionController', ['$scope', '$http', function($scope, $http) {
            var vm = this;
            vm.gameMode = true;
            vm.questions = [];
            vm.question = undefined;
            vm.getAll = getAll; // used for updates
            vm.create = createQuestion;
            vm.delete = deleteQuestion;
            vm.getRandomQuestion = getRandomQuestion;

            vm.counter = 0;
            vm.maxQuestions = 5;
            vm.good = 0;

            init();

            vm.start = function () {
                vm.gameMode = !vm.gameMode;
            };

            vm.gen = gen;

            function gen(good) {
                if (vm.counter < vm.maxQuestions - 1) {
                    if (good) {
                        swal({title: 'Good job!', text: '', type: 'success', timer: 800, showConfirmButton: false});
                        vm.good += 1;
                    } else {
                        swal({title:'Wrong!', text:'', type: 'error', timer: 800, showConfirmButton : false});
                    }
                } else {
                    if (good)
                        vm.good += 1;
                }
                vm.counter += 1;
                if (vm.counter < vm.maxQuestions) {
                    vm.getRandomQuestion();
                } else {
                    swal({
                            title: "Question",
                            text: "Add a new question:",
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

                            swal("Now please add a good answer to your question", "You wrote: " + inputValue, "success");
                            swal({
                                title: "Answer",
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
                    //swal({title:'Wrong!', text:"You reponded to " + vm.good * 10 + "% good questions" , type: 'success', closeOnConfirm: true, timer: 160000, showConfirmButton : true});
                    // vm.counter = 0;
                    // vm.good = 0;
                    // gen();
                }
            }

            function init(){
                getAll();
            }

            function getAll(){
                var url = "/questions/all";
                var questionsPromise = $http.get(url);
                questionsPromise.then(function(response){
                    vm.questions = response.data;
                }).then(function (){
                    getRandomQuestion();
                });
            }

            function randMax(nbr) {
                return Math.floor(Math.random() * nbr);
            }

            // PRE: WORKS ONLY IF GETALL WAS CALLED
            function getRandomQuestion() {
                var nbr = vm.questions.length;
                vm.question = [vm.questions[randMax(nbr)]];

                //startTimer();
            }

            function startTimer() {
                var seconds, twentysecs = 10;

                var time = setInterval(function() {
                    seconds = parseInt(twentysecs % 20);
                    seconds = seconds < 10 ? '0' + seconds : seconds;

                    timer.innerHTML ='00:' + seconds;
                    twentysecs--;

                    if(twentysecs < 0){
                        clearInterval(time);
                        console.log('time up');
                        vm.gen(false);
                    }

                }, 1000);

            }



            //Caution: Not implemented in Java
            function createQuestion(){
                var url = "/questions/create";
                var questionsPromise = $http.post(url);
                questionsPromise.then(function(response){
                    vm.questions = response.data;
                });
            }

            function deleteQuestion(id) {
                var url = "/questions/delete/" + id;
                $http.get(url).then(function (response) {
                    vm.questions = response.data;
                });
            }
        }]);
})();
