/**
 * Created by ad5915 on 06/06/17.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavigationController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.gameMode = false;
            vm.startSingle = startSingle;

            function startSingle() {
                vm.gameMode = !vm.gameMode;

                var promise = $http.get("/session/single");
                promise.then(function () {
                    console.log("Started new game.");
                });
            }

            function init() {

            }

            init();


        }]);
})();