'use strict';

app.controller('RoleController', ['$http', function($http) {

    var vm = this;

    vm.roles = [];
    vm.getAll = getAll;
    vm.create = createRole;
    vm.delete = deleteRole;

    init();

    function init(){
        getAll();
    }

    function getAll(){
        var url = "/roles/all";
        var rolesPromise = $http.get(url);
        rolesPromise.then(function(response){
            vm.roles = response.data;
        });
    }

    function createRole(){
        var url = "/roles/create";
        var rolesPromise = $http.post(url);
        rolesPromise.then(function(response){
            vm.roles = response.data;
        });
    }

    function deleteQuestion(id) {
        var url = "/roles/delete/" + id;
        $http.get(url).then(function (response) {
            vm.roles = response.data;
        });
    }

}]);