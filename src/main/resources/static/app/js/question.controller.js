/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.endGame = false; // refactoring

            vm.getQuestion = randomQuestion;
            vm.gen = gen;
            vm.question = null;

            function randomQuestion() {
                $http.get("/questions/random").then(function (response) {
                    console.log("New question generated. ");
                    vm.question = [response.data];
                });
            }

            function gen(mybool) {
            }

            init();

            function init() {
                randomQuestion();
            }
        }]);
})();
