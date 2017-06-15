/**
 * Created by adicatana on 14.06.2017.
 */

'use strict';

app.controller('ProfileController', ['$http', 'getGamePhase',
    function($http, getGamePhase) {

        var vm = this;

        vm.enterProfile = enterProfile;
        vm.exitProfile = exitProfile;

        function enterProfile() {
            $http.get("/profile/enter").then(function() {
                console.log("Entered inside profile page.");
            }).then(function() {
                getGamePhase();
            });
        }

        function exitProfile() {
            $http.get("/profile/exit").then(function() {
                console.log("Exit from profile page.");
                getGamePhase();
            });
        }
    }
]);