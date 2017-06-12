'use strict';

app.controller('LoginController', ['$rootScope', '$scope', '$http', 'getGamePhase',
    function($rootScope, $scope, $http, getGamePhase) {

    $rootScope.fbEnsureInit(function() {
        console.log("This will be run once FB is initialized\n");
        (function() {
            FB.api('/me', function(res) {
                $rootScope.$apply(function() {
                    var user = res;

                    console.log(user);

                    if (user.error !== undefined) {
                        console.log("User could not be found.\n");
                        return;
                    }

                    console.log("Starting session for user: " + user.id);

                    var getUri = "/session/start/" + user.id;
                    console.log("Resources: " + getUri);

                    $http.get(getUri).then(function() {
                        console.log("Session started for user: " + user.id);
                    }).then(function() {
                        getGamePhase();
                    });
                });
            });
        })();
    });

}]);

app.run(['$http', '$window', '$rootScope', function($http, $window, $rootScope) {

    $window.fbAsyncInit = function() {
        window.fbApiInit = false;
        FB.init({
            appId: '474282212910062',
            status: true,
            cookie: true,
            xfbml: true
        });

        FB.getLoginStatus(function(response) {
            window.fbApiInit = true;
        });

    };

    $rootScope.fbEnsureInit = function(callback) {
        if (!window.fbApiInit) {
            setTimeout(function() {
                $rootScope.fbEnsureInit(callback);
            }, 50);
        } else {
            if (callback) {
                callback();
            }
        }
    };

    (function(d) {
        var js,
            id = 'facebook-jssdk',
            ref = d.getElementsByTagName('script')[0];

        if (d.getElementById(id)) {
            return;
        }

        js = d.createElement('script');
        js.id = id;
        js.async = true;
        js.src = "//connect.facebook.net/en_US/all.js";

        ref.parentNode.insertBefore(js, ref);
    }(document));

}]);