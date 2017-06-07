'use strict';
app.controller('TopicController', ['$scope', '$http', function($scope, $http) {

    function init() {
        $http.get('/topics/all').then(function(response) {
            $scope.topics = response.data;
        });
    }

    init();

    $(function () {
        $( '#searchable-container' ).searchable({
            searchField: '#container-search',
            selector: '.col-sm-11',
            childSelector: '.panel-title',
            show: function( elem ) {
                elem.slideDown(100);
            },
            hide: function( elem ) {
                elem.slideUp( 100 );
            }
        })
    });
    // $scope.topics = [[{topicname: "Topic1"}, {topicname: "Topic2"}, {topicname: "Topic3"}]];

}]);
