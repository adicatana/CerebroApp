/**
 * Created by adicatana on 20.06.2017.
 */
'use strict';

app.controller('AdminController', ['$http', 'getGamePhase', function ($http, getGamePhase) {
    var vm = this;
    vm.questionList = [];

    vm.enterAdmin = function () {
        $http.get("/admin/enter").then(function() {
            console.log("Entered inside admin page.");
        }).then(function() {
            getGamePhase();
        });
    };

    vm.exitAdmin = function () {
        $http.get("/admin/exit").then(function() {
            console.log("Exited the admin page.");
        }).then(function() {
            getGamePhase();
        });
    };

    vm.approve = function () {
        var qJSONString = JSON.stringify(vm.questionList);
        $http.post("/admin/approve", qJSONString).then(function () {
            console.log("Sent modified questions to DB " + qJSONString);
        }).then(function () {
            getQuestions();
        });
    };

    function getQuestions() {
        $http.get("/admin/pending").then(function(result) {
            console.log("Getting pending questions.");
            vm.questionList = result.data;
            console.log(vm.questionList);
        });
    };

    init();

    function init() {
        getQuestions();
    }
    
}]);