'use strict';

app.controller('IndexController', ['$http', function($http) {

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
    }

}]);
