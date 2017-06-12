/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';
app.controller('NavigationController', ['$scope', '$http', '$rootScope', 'currentUser',
    function($scope, $http, $rootScope, currentUser) {
        var vm = this;

        vm.startSingle = startSingle;
        vm.getGamePhase = getGamePhase;
        vm.topicSingle = topicSingle;
        window.getGamePhase = getGamePhase;

        vm.gamePhase = "";

        function topicSingle() {
            $http.get("/singleplayer/topic").then(function() {
                console.log("Topic selected.");
            }).then(function() {
                getGamePhase();
            });
        }

        function startSingle(topic) {
            $http.get("/singleplayer/single/" + topic).then(function() {
                console.log("Started new game.");
            }).then(function() {
                getGamePhase();
            });
        }

        function getGamePhase() {
            $http.get("/session/phase").then(function(response) {
                vm.gamePhase = response.data;
                console.log("Game phase updated to:" + vm.gamePhase);

                if (vm.gamePhase === "LOGGED") {
                    console.log("Logged !");
                }
                if (vm.gamePhase !== "NONE") {
                    currentUser();
                }
            });
        }

        function init() {
            getGamePhase();

            /* Register a public service for getting game phase. */
            $scope.$on('getGamePhase', function() {
                getGamePhase();
            });
        }

        init();
    }
]);