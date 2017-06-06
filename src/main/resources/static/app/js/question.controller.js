'use strict';

app.controller('QuestionController', ['$scope', '$http', function($scope, $http) {

    var vm = this;
    vm.gameMode = true;
    vm.endGame = false;
    vm.questions = [];
    vm.question = undefined;
    vm.getAll = getAll; // used for updates
    vm.create = createQuestion;
    vm.delete = deleteQuestion;
    vm.getRandomQuestion = getRandomQuestion;

    vm.counter = 0;
    vm.maxQuestions = 5;
    vm.good = 0;
    vm.lastGood = 0;

    init();

    vm.start = function () {
        vm.gameMode = !vm.gameMode;
    };

    vm.gen = gen;

    function gen(good) {
        if (vm.counter === vm.maxQuestions) {
            vm.counter = 0;
            vm.good = 0;
            vm.gameMode = !vm.gameMode;
            vm.endGame = false;
            survey();
            return;
        }
        vm.counter += 1;
        swal({
            title: good ? 'Good job!' : "Wrong!",
            text: good ? '' : "Correct answer: " + vm.question[0].answer,
            type: good ? 'success' : 'error',
            timer: good ? 800 : 2000,
            showConfirmButton: false
        });
        vm.good += good ? 1 : 0;
        if (vm.counter < vm.maxQuestions) {
            vm.getRandomQuestion();
        } else {
            vm.lastGood = [vm.good];
            vm.endGame = true;
        }
    }

    function survey() {
        swal({
                title: "Contribute",
                text: "To contribute to our content, please add a question to be reponded by other players:",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                inputPlaceholder: "Write something"
            },
            function(inputValue){
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
                    function(inputValue){
                        if (inputValue === false) return false;

                        if (inputValue === "") {
                            swal.showInputError("You need to write something!");
                            return false
                        }
                        swal("Thank you for your input!", "" + inputValue, "success");
                    });
            });
    }

    function init(){
        getAll();
    }

    function getAll(){
        var url = "/questions/all";
        var questionsPromise = $http.get(url);
        questionsPromise.then(function(response){
            vm.questions = response.data;
        }).then(function (){
            getRandomQuestion();
        });
    }

    function randMax(nbr) {
        return Math.floor(Math.random() * nbr);
    }

    // PRE: WORKS ONLY IF GETALL WAS CALLED
    function getRandomQuestion() {
        var nbr = vm.questions.length;
        vm.question = [vm.questions[randMax(nbr)]];

        //startTimer();
    }

    function startTimer() {
        var seconds, twentysecs = 10;

        var time = setInterval(function() {
            seconds = parseInt(twentysecs % 20);
            seconds = seconds < 10 ? '0' + seconds : seconds;

            timer.innerHTML ='00:' + seconds;
            twentysecs--;

            if(twentysecs < 0){
                clearInterval(time);
                console.log('time up');
                vm.gen(false);
            }

        }, 1000);

    }



    //Caution: Not implemented in Java
    function createQuestion(){
        var url = "/questions/create";
        var questionsPromise = $http.post(url);
        questionsPromise.then(function(response){
            vm.questions = response.data;
        });
    }

    function deleteQuestion(id) {
        var url = "/questions/delete/" + id;
        $http.get(url).then(function (response) {
            vm.questions = response.data;
        });
    }
}]);
