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
                vm.password = $scope.passUser;
                // vm.user = document.getElementsByTagName("inputUser")[0];
                // vm.password =  document.getElementsByTagName("inputPass")[0];
                //
                // console.log(document.getElementsByTagName("inputUser")[0]);

                console.log(vm.user);
                console.log(vm.password);

                // setTimeout(function(){
                    startSession();
                // }, 100);
            }

            function startSession() {
                var url = "/session/start";
                var data = {
                    "login" : vm.user,
                    "password" : vm.password
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
