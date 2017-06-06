/**
 * Created by ad5915 on 06/06/17.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavigationController', ['$scope', '$http', function($scope, $http) {
            var vm = this;

            vm.startSingle = startSingle;
            vm.getGamePhase = getGamePhase;
            vm.gamePhase = "";

            function startSingle() {
                $http.get("/singleplayer/single").then(function () {
                    console.log("Started new game.");
                }).then(function () {
                    getGamePhase();
                });
            }

            function getGamePhase() {
                $http.get("/session/phase").then(function (response) {
                    vm.gamePhase = response.data;
                    console.log("Game phase updated to:" + vm.gamePhase);
                });
            }

            function init() {
                getGamePhase();
            }

            init();
        }]);
})();