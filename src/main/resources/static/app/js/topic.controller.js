'use strict';
app.controller('TopicController', ['$scope', '$http', function($scope, $http) {

    function init() {
        $http.get('/topics/misc').then(function(response) {
            $scope.miscTopics = response.data;
        });
    }

    init();

    $scope.isBook = false;

    $scope.getItems = function(isBook) {
        $scope.isBook = isBook;
        if(isBook) {
            $http.get('/topics/books').then(function(response) {
                $scope.miscTopics = response.data;
            });
        } else {
            $http.get('/topics/misc').then(function(response) {
                $scope.miscTopics = response.data;
            });
        }
    }

}]);
