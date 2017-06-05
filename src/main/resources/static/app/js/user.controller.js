/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', ['$scope', '$http', function($scope, $http) {

            var vm = this;


            vm.users = [];
            vm.getAll = getAll;
            vm.create = createUser;
            vm.delete = deleteUser;

            init();

            function init(){
                getAll();
            }

            function getAll(){
                var url = "/users/all";
                var usersPromise = $http.get(url);
                usersPromise.then(function(response){
                    vm.users = response.data;
                });
            }

            //Caution: Not implemented in Java
            function createUser(){
                var url = "/users/create";
                var usersPromise = $http.post(url);
                usersPromise.then(function(response){
                    vm.users = response.data;
                });
            }

            function deleteUser(id) {
                var url = "/users/delete/" + id;
                $http.get(url).then(function (response) {
                    vm.users = response.data;
                });
            }

        }]);
})();
