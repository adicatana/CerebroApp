/**
 * Created by ad5915 on 01/06/17.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('IndexController', [IndexController]);

    IndexController.$inject = ['$http'];

    function IndexController($http) {
        var vm = this;

        vm.counter = 0;
        vm.maxQuestions = 10;
        vm.next = 0;

        function init() {
            console.log("HELLLLO");
            alert("here");
            angular.element(document).ready(function () {
                vm.registerListeners();
            });
        }

        init();

        vm.registerListeners = registerListeners;

        function registerListeners() {
            document.getElementById("questionbutton").addEventListener("click", function () {
                vm.counter += 1;
                document.getElementById("questionbutton").innerHTML = "Hello World";
                alert(vm.counter);
            });
        };
    }
})();
