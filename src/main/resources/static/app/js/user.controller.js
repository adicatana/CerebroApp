(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserProfileController', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
            var vm = this;

            vm.users = [];
            vm.getCurrentUser = getCurrentUser;
            window.getCurrentUser = getCurrentUser;
            $rootScope.rate = getCurrentUser;


            vm.currentUser = undefined;
            vm.pictureSrc = undefined;

            init();

            function init(){
                getCurrentUser();
            }

            function getCurrentUser() {
                console.log("Entered");

                var url = "/profile/user";
                $http.get(url).then(function (response) {
                    vm.currentUser = response.data;
                    var picSrc = "https://graph.facebook.com/" + vm.currentUser.login + "/picture?type=large&redirect=true&width=650&height=650"
                    $http.get(picSrc).then(function (picResponse) {
                        vm.pictureSrc = picResponse.data;
                        console.log(picResponse);
                    })
                });

            }

        }]);
})();
