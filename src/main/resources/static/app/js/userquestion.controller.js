'use strict';

app.controller('UserQuestionController', ['$http', function($http) {

    var vm = this;


    vm.userXquestions = [];
    vm.getAll = getAll;
    vm.create = createUserQuestion;
    vm.delete = deleteUserQuestion;

    init();

    function init(){
        getAll();
    }

    function getAll(){
        var url = "/userquest/all";
        var userXquestionPromise = $http.get(url);
        userXquestionPromise.then(function(response){
            vm.userXquestions = response.data;
        });
    }

    //Caution: Not implemented in Java
    function createQuestion(){
        var url = "/userquest/create";
        var userXquestionPromise = $http.post(url);
        userXquestionPromise.then(function(response){
            vm.userXquestions = response.data;
        });
    }

    function deleteQuestion(id) {
        var url = "/userquest/delete/" + id;
        $http.get(url).then(function (response) {
            vm.userXquestions = response.data;
        });
    }

}]);
