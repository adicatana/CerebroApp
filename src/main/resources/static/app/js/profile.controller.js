/**
 * Created by adicatana on 14.06.2017.
 */

'use strict';

app.controller('ProfileController', ['$scope', '$rootScope', '$http', 'getGamePhase',
    function($scope, $rootScope, $http, getGamePhase) {

        var vm = this;

        vm.enterProfile = enterProfile;
        vm.exitProfile = exitProfile;

        getTopicStatistics();

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

        function getTopicStatistics() {
            $http.get("/profile/progress").then(function(result) {
                console.log("Get progress info.");
                $rootScope.progressList = result.data;
                $rootScope.rightList = [];
                $rootScope.wrongList = [];
                for (var i = 0; i < result.data.length; i++) {
                    $rootScope.rightList.push(Math.floor(result.data[i].rightAnswers * 100 / result.data[i].totalAnswers));
                    $rootScope.wrongList.push(Math.floor(result.data[i].wrongAnswers * 100 / result.data[i].totalAnswers));
                }
            }).then(function () {
                console.log($rootScope.rightList);
                console.log($rootScope.wrongList);
            });
        }

        $scope.functionThatReturnsStyle = function(number) {
            console.log("NUMBER HERE " + number);
            var style1 = "width: " + number + "%";
            return style1;
        }

    }
]);