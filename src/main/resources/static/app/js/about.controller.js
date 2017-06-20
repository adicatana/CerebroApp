/**
 * Created by adicatana on 20.06.2017.
 */

'use strict';

app.controller('AboutController', ['$http', 'getGamePhase', function ($http, getGamePhase) {

    var vm = this;

    vm.enterAbout = function () {
        $http.get("/about/enter").then(function() {
            console.log("Entered inside about page.");
        }).then(function() {
            getGamePhase();
        });
    };

    vm.exitAbout = function () {
        $http.get("/about/exit").then(function() {
            console.log("Exited the about page.");
        }).then(function() {
            getGamePhase();
        });
    };

}]);