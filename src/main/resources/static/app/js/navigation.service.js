/**
 * Created by ad5915 on 12/06/17.
 */

'use strict';

app.factory('getGamePhase', ['$http', '$rootScope', 'currentUser',
    function($http, $rootScope, currentUser) {
        // A public service that requests from the backend the current game phase.
        return function() {
            $http.get("/session/phase").then(function(response) {
                $rootScope.gamePhase = response.data;
                console.log("Game phase updated to:" + $rootScope.gamePhase);

                if ($rootScope.gamePhase === "LOGGED") {
                    console.log("Logged !");
                }
                if ($rootScope.gamePhase !== "NONE") {
                    currentUser();
                }
            });
    };
}]);