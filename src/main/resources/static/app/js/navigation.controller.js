/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';
app.controller('NavigationController', ['$scope', '$http', '$rootScope', 'getGamePhase',
    function($scope, $http, $rootScope, getGamePhase) {
        var vm = this;

        vm.startSingle = startSingle;
        vm.topicSingle = topicSingle;
        vm.startMulti = startMulti;

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

        function startMulti() {
            $http.get("multi/join").then(function() {
                console.log("Joining game room.");
                $http.get("multi/match").then(function (response) {
                    console.log("Started match.");
                    vm.match = response.data;
                }).then(function () {
                    getGamePhase();
                });
            });
        }

        function init() {
            getGamePhase();
        }

        init();
    }
]);