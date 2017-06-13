/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';

app.controller('FeedbackController', ['$scope', '$http', 'getGamePhase',
    function($scope, $http, getGamePhase) {

    var vm = this;

    vm.percent = 0;
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
        '<input id="topic" class="swal2-input" placeholder="Topic">' +
        '<input id="question" class="swal2-input" placeholder="Question">' +
        '<input id="answer" class="swal2-input" placeholder="Correct answer">' +
        '<input id="wrong1" class="swal2-input" placeholder="First wrong answer">' +
        '<input id="wrong2" class="swal2-input" placeholder="Second wrong answer">';

    function collectFeedback() {
        swal({
            title: 'Contribute',
            html: swalHTMLContent,
            preConfirm: function () {
                return new Promise(function (resolve) {
                    resolve([
                        $('#topic').val(),
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
            // swal(JSON.stringify(userInputJSON)) // used for debugging
            sendUserData(userArrayInput);
        }).catch(swal.noop);
        gotoMainScreen();
    }

    function sendUserData(userArrayInput) {
        var userJSONInput = {
            topic: {
                topicname: userArrayInput[0]
            },
            question: userArrayInput[1],
            answer: userArrayInput[2],
            wrong1: userArrayInput[3],
            wrong2: userArrayInput[4]
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