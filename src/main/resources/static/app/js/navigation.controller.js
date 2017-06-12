/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';
app.controller('NavigationController', ['$scope', '$http', '$rootScope', 'getGamePhase',
    function($scope, $http, $rootScope, getGamePhase) {
        var vm = this;

        vm.startSingle = startSingle;
        vm.topicSingle = topicSingle;

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

        function init() {
            getGamePhase();
        }

        init();
    }
]);