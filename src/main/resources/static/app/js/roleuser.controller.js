'use strict';

app.controller('RoleUserController', ['$http', function($http) {

    var vm = this;


    vm.roleXusers = [];
    vm.getAll = getAll;
    vm.create = createRoleXuser;
    vm.delete = deleteRoleXuser;

    init();

    function init(){
        getAll();
    }

    function getAll(){
        var url = "/roleusers/all";
        var roleusePromise = $http.get(url);
        roleusePromise.then(function(response){
            vm.roleXusers = response.data;
        });
    }

    function createRoleXuser(){
        var url = "/roleusers/create";
        var roleuserPromise = $http.post(url);
        roleuserPromise.then(function(response){
            vm.roleXusers = response.data;
        });
    }

    function deleteRoleXuser(id) {
        var url = "/roleusers/delete/" + id;
        $http.get(url).then(function (response) {
            vm.roleXusers = response.data;
        });
    }

}]);
