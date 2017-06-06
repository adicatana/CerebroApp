'use strict';
app.controller('TopicController', ['$scope', '$http', function($scope, $http) {

    function init() {
        $http.get('/topics/all').then(function(response) {
            var seq_length = 3;
            var temparray;
            $scope.topics = [];
            for (var i = 0,j = response.data.length; i < j; i += seq_length) {
                temparray = response.data.slice(i, i + seq_length);
                $scope.topics.push(temparray);
            }
        });
    }

    init();
    // $scope.topics = [[{topicname: "Topic1"}, {topicname: "Topic2"}, {topicname: "Topic3"}]];

}]);
