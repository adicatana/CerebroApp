

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

            vm.user = "";
            vm.password = "";

            function record() {
                vm.user = $scope.inputUser;
                vm.password = $scope.inputPass;

                console.log(vm.user);
                console.log(vm.password);

                startSession();
                document.forms[0].submit();
            }

            function startSession() {
                var url = "/session/start";
                var data = {
                    login : vm.user,
                    password : vm.password
                };

                console.log(vm.user);
                console.log(vm.password);

                $http.post(url, data).then(function () {
                    console.log("Started new session for user " + vm.user);
                });
            }

            init();

            function init() {
            }
        }])
})();

