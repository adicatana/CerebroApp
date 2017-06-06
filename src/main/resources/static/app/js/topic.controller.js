'use strict';
app.controller('TopicController', ['$scope', '$http', function($scope, $http) {

    function init() {
        $http.get('/topics/all').then(function(data) {
            $scope.topics = data;
            console.log(data);
        });
    }

    init();
    // $scope.topics = [{name: "Topic1"}, {name: "Topic2"}, {name: "Topic3"}, {name: "Topic4"}];

}]);
