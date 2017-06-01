/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.questions = [];
            vm.question = undefined;
            vm.getAll = getAll; // used for updates
            vm.create = createQuestion;
            vm.delete = deleteQuestion;
            vm.getRandomQuestion = getRandomQuestion;

            init();

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
