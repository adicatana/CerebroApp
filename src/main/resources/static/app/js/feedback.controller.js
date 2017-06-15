/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';

app.controller('FeedbackController', ['$scope', '$http', 'getGamePhase',
    function($scope, $http, getGamePhase) {

    var vm = this;

    vm.percent = 0;
    vm.topics = [];
    vm.getPercentage = getPercentage;
    vm.endGame = endGame;

    function getPercentage() {
        $http.get("/singleplayer/score/").then(function(response) {
            vm.percent = response.data;
        });
    }

    function endGame() {
        collectFeedback();
    }

    function gotoMainScreen() {
        $http.get("/feedback/end").then(function() {
            console.log("The game has ended. Returning to main screen.");
            getGamePhase();
        });
    }

    var swalHTMLContent =
        '<p>To contribute to our content, please add a question for other players:</p>' +
        '<input id="question" class="swal2-input" placeholder="Question">' +
        '<input id="answer" class="swal2-input" placeholder="Correct answer">' +
        '<input id="wrong1" class="swal2-input" placeholder="First wrong answer">' +
        '<input id="wrong2" class="swal2-input" placeholder="Second wrong answer">';

    function collectFeedback() {
        swal({
            title: 'Contribute',
            html: swalHTMLContent,
            showCancelButton: true,
            preConfirm: function () {
                return new Promise(function (resolve) {
                    resolve([
                        $('#question').val(),
                        $('#answer').val(),
                        $('#wrong1').val(),
                        $('#wrong2').val()
                    ]);
                });
            },
            onOpen: function () {
                $('#swal-input1').focus();
            }
        }).then(function (userArrayInput) {
            getAllTopicsAndRequest(userArrayInput, topicSelection);
        }).catch(swal.noop);
        gotoMainScreen();
    }

    function topicSelection(userArrayInput) {
        swal({
            title: 'Contribute - Topic',
            input: 'select',
            inputOptions: vm.topics,
            inputPlaceholder: 'Select a topic',
            showCancelButton: true
        }).then(function(selection) {
            sendUserData(userArrayInput, selection);
        });
    }

    function getAllTopicsAndRequest(userInputArray, callback) {
        function formatAsMap(list) {
            var ans = {};
            for (var i = 0; i < list.length; ++i) {
                var topic = list[i];
                console.log(topic);

                ans[topic.id] = topic.topicname;
            }
            return ans;
        }

        $http.get('/topics/all').then(function(response) {
            vm.topics = formatAsMap(response.data);
            console.log("Topics: " + vm.topics);

            // The swal function.
            callback(userInputArray);
        });
    }

    function sendUserData(userArrayInput, topicId) {
        var userJSONInput = {
            topic: {
                id: parseInt(topicId)
            },
            question: userArrayInput[0],
            answer: userArrayInput[1],
            wrong1: userArrayInput[2],
            wrong2: userArrayInput[3]
        };
        var userJSONString = JSON.stringify(userJSONInput);

        console.log("Sending: ", userJSONString);
        $http.post("/feedback/input", userJSONString).then(function() {
            console.log("User feedback sent:", userJSONString);
        });
    }

    init();

    function init() {
        getPercentage();
    }
}]);