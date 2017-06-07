

/**
 * Created by ad5915 on 05/06/17.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', ['$rootScope', '$http', '$scope',
            function($rootScope, $http, $scope) {
            var vm = this;

            vm.getUserInfo = getUserInfo;
            $rootScope.getUserInfo = getUserInfo;

            function getUserInfo() {
                $rootScope.facebookAPI.api('/me', function(res) {
                    $rootScope.$apply(function() {
                        $rootScope.user = res;

                        console.log("Starting session for user: " + $rootScope.user.id);
                        var getUri = "/session/start/" + $rootScope.user.id;
                        console.log("Resources: " + getUri);
                        $http.get(getUri).then(function () {
                            console.log("Session started for user: " + $rootScope.user.id);
                        }).then(function() {
                            $scope.$emit('getGamePhase');
                        });
                    });
                });
            }

            init();

            function init() {
            }
        }]);

    angular.module('app').run(['$rootScope', '$window',
        function($rootScope, $window) {

            $rootScope.user = {};

            $window.fbAsyncInit = function() {
                FB.init({
                    appId: '474282212910062',
                    status: true,
                    cookie: true,
                    xfbml: true
                });
                $rootScope.facebookAPI = FB;

                setTimeout(function(){
                    $rootScope.getUserInfo();
                }, 1000);
            };

            (function(d){
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
})();
