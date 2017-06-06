

/**
 * Created by ad5915 on 05/06/17.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.record  = record;
            vm.startSession = startSession;
            vm.getStatus = getStatus;

            vm.user = "";
            vm.password = "";
            vm.token = "";

            function record() {
                vm.user = $scope.inputUser;
                vm.password = $scope.inputPass;

                console.log(vm.user);
                console.log(vm.password);

                startSession();
            }

            function getStatus() {
                FB.getLoginStatus(function(response) {
                    if (response.data.status === "connected") {
                        vm.token = response.data.authResponse.accessToken;
                        console.log(vm.token);
                    }
                });

            }

            function startSession() {
                var url = "/session/start";
                var data = {
                    login : vm.user,
                    password : vm.password
                };

                console.log(vm.user);
                console.log(vm.password);

                var promise = $http.post(url, data);
                promise.then(function () {
                    console.log("Started new session for user " + vm.user);

                    document.forms[0].submit();
                });
            }

            init();

            function init() {
            }
        }])
})();

