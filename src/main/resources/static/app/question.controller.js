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
            vm.getAll = getAll;
            vm.create = createQuestion;
            vm.delete = deleteQuestion;

            init();

            function init(){
                getAll();
            }

            function getAll(){
                var url = "/roleuser/all";
                var questionsPromise = $http.get(url);
                questionsPromise.then(function(response){
                    vm.questions = response.data;
                });
            }

            //Caution: Not implemented in Java
            function createQuestion(){
                var url = "/roleuser/create";
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
