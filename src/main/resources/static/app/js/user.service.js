'use strict';

app.factory('currentUser', ['$http', '$rootScope', function($http, $rootScope) {
    return function() {
        console.log("Entered");
        var url = "/profile/user";
        $http.get(url).then(function(response) {
            $rootScope.currentUser = response.data;
        });
    };
}]);