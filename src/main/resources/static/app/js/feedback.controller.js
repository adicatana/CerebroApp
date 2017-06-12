/**
 * Created by ad5915 on 06/06/17.
 */
'use strict';

app.controller('FeedbackController', ['$scope', '$http', function($scope, $http) {
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

            /* Call a public event. */
            $scope.$emit('getGamePhase');
        });
    }

    function collectFeedback() {
        swal({
                title: "Contribute",
                text: "To contribute to our content, please add a question to be reponded by other players:",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                inputPlaceholder: "Write something"
            },
            function(inputValue) {
                if (inputValue === false) return false;

                if (inputValue === "") {
                    swal.showInputError("You need to write something!");
                    return false;
                }

                swal("Now please add a good answer to your question", "You wrote: " + inputValue, "success");
                swal({
                        title: "Contribute: Answer",
                        text: "Now please add a good answer to your question:",
                        type: "input",
                        showCancelButton: true,
                        closeOnConfirm: false,
                        animation: "slide-from-top",
                        inputPlaceholder: "Write something"
                    },
                    function(inputValue) {
                        if (inputValue === false) return false;

                        if (inputValue === "") {
                            swal.showInputError("You need to write something!");
                            return false
                        }
                        swal("Thank you for your input!", "" + inputValue, "success");
                    });
            });
        gotoMainScreen();
    }

    init();

    function init() {
        getPercentage();
    }
}]);