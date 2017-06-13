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

    function collectFeedback() {
        swal({
            title: 'Contribute',
            html:
            '<p>To contribute to our content, please add a question for other players:</p>' +
            '<input id="topic" class="swal2-input" placeholder="Topic">' +
            '<input id="question" class="swal2-input" placeholder="Question">' +
            '<input id="answer" class="swal2-input" placeholder="Correct answer">' +
            '<input id="wrong1" class="swal2-input" placeholder="First wrong answer">' +
            '<input id="wrong2" class="swal2-input" placeholder="Second wrong answer">',
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
        }).then(function (result) {
            // swal(JSON.stringify(result))
        }).catch(swal.noop);
        //
        //
        // swal({
        //         title: "Contribute",
        //         text: "To contribute to our content, please add a question to be reponded by other players:",
        //         type: "input",
        //         showCancelButton: true,
        //         closeOnConfirm: false,
        //         animation: "slide-from-top",
        //         inputPlaceholder: "Write something"
        //     },
        //     function(inputValue) {
        //         if (inputValue === false) return false;
        //
        //         if (inputValue === "") {
        //             swal.showInputError("You need to write something!");
        //             return false;
        //         }
        //
        //         swal("Now please add a good answer to your question", "You wrote: " + inputValue, "success");
        //         swal({
        //                 title: "Contribute: Answer",
        //                 text: "Now please add a good answer to your question:",
        //                 type: "input",
        //                 showCancelButton: true,
        //                 closeOnConfirm: false,
        //                 animation: "slide-from-top",
        //                 inputPlaceholder: "Write something"
        //             },
        //             function(inputValue) {
        //                 if (inputValue === false) return false;
        //
        //                 if (inputValue === "") {
        //                     swal.showInputError("You need to write something!");
        //                     return false
        //                 }
        //                 swal("Thank you for your input!", "" + inputValue, "success");
        //             });
        //     });
        gotoMainScreen();
    }

    init();

    function init() {
        getPercentage();
    }
}]);