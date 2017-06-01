/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RoleUserController', ['$scope', '$http', function($scope, $http) {

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
})();
