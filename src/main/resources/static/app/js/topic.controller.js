/**
 * Created by andrei-octavian on 01.06.2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('TopicController', ['$scope', '$http', function($scope, $http) {

            var vm = this;


            vm.topics = [];
            vm.getAll = getAll;
            vm.create = createTopic;
            vm.delete = deleteTopic;

            init();

            function init(){
                getAll();
            }

            function getAll(){
                var url = "/topic/all";
                var topicsPromise = $http.get(url);
                topicsPromise.then(function(response){
                    vm.topics = response.data;
                });
            }

            //Caution: Not implemented in Java
            function createTopic(){
                var url = "/topics/create";
                var topicsPromise = $http.post(url);
                topicsPromise.then(function(response){
                    vm.topics = response.data;
                });
            }

            function deleteTopic(id) {
                var url = "/topics/delete/" + id;
                $http.get(url).then(function (response) {
                    vm.topics = response.data;
                });
            }

        }]);
})();
