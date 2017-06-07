/**
 * Created by ad5915 on 07/06/17.
 */

(function () {
    'use strict';

    angular
        .module('app')
        .controller('RankingsController', ['$rootScope','$scope', '$http', function($rootScope, $scope, $http) {
            var vm = this;

            vm.enterRankings = enterRankings;
            vm.exitRankings = exitRankings;
            vm.getRankings = getRankings;
            vm.userList = [];

            function enterRankings() {
                $http.get("/rankings/enter").then(function (){
                   console.log("Entered inside rankings page.");
                }).then(function () {
                   getRankings();
                   $scope.$emit('getGamePhase');
                });
            }

            function exitRankings() {
                $http.get("/rankings/exit").then(function (){
                    console.log("Exit from rankings page.");
                    $scope.$emit('getGamePhase');
                });
            }

            function getRankings() {
                $http.get("/rankings/all/sorted").then(function (result){
                    console.log("Getting rankings.");
                    vm.userList = result.data;
                    console.log(vm.userList);
                });
            }

            init();

            function init() {
                $http.get("/rankings/all/sorted").then(function (result){
                    console.log("Getting rankings.");
                    vm.userList = result.data;
                    console.log(vm.userList);
                });
            }
        }]);
})();