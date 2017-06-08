'use strict';
app.controller('TopicController', ['$scope', '$http', function($scope, $http) {

    function init() {
        $http.get('/topics/all').then(function(response) {
            $scope.topics = response.data;
        });
    }

    init();

}]);
