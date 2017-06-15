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

        // Used so double click has no effect.
        var canStartMulti = true;

        function startMulti() {
            if (!canStartMulti) {
                return;
            }
            $http.get("multi/join").then(function() {
                canStartMulti = false;
                console.log("Joining game room.");
                swal({
                    title: 'Waiting for an opponent.',
                    showConfirmButton: false,
                    onOpen: function() {
                        return new Promise(function () {
                            $http.get("multi/match").then(function (response) {
                                canStartMulti = true;
                                console.log("Started match.");
                                vm.match = response.data;
                            }).then(function () {
                                getGamePhase();
                                swal.closeModal();
                            });
                        });
                    },
                    allowOutsideClick: false
                });
            });
        }

        function init() {
            getGamePhase();
        }

        init();
    }
]);