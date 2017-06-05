/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RoleController', ['$scope', '$http', function($scope, $http) {

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
})();
