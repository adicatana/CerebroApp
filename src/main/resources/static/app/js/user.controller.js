(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserProfileController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.users = [];
            vm.getCurrentUser = getCurrentUser;
            document.getCurrentUser = getCurrentUser;

            vm.currentUser = undefined;

            init();

            function init(){
                getCurrentUser();
            }

            function getCurrentUser(){
                console.log("Entered");

                var url = "/profile/user";
                $http.get(url).then(function(response){
                    vm.currentUser = response.data;
                    console.log(vm.currentUser.login);
                });

            }

        }]);
})();
