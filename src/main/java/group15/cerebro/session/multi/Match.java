package group15.cerebro.session.multi;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Usr;

public class Match {
    private Usr player1;
    private Usr player2;
    private static final int TOTAL = 10;
    private int remainingQuestions;

    private int correct1;
    private int correct2;

    private boolean player1answered;
    private boolean player2answered;

    private Question question;

    public Match(Usr player1, Usr player2) {
        this.player1 = player1;
        this.player2 = player2;

        correct1 = 0;
        correct2 = 0;

        question = null;
        player1answered = false;
        player2answered = false;
        remainingQuestions = getTOTAL();
    }

    public static int getTOTAL() {
        return TOTAL;
    }

    public synchronized Usr getPlayer1() {
        return player1;
    }

    public synchronized Usr getPlayer2() {
        return player2;
    }

    public synchronized int getCorrect1() {
        return correct1;
    }

    public synchronized int getCorrect2() {
        return correct2;
    }

    public synchronized Question getQuestionRandomized() {
        return new QuestionRandomizer().randomizeOrder(question);
    }

    public synchronized Question getQuestion() {
        return question;
    }

    public synchronized void setQuestion(Question question) {
        this.question = question;
    }

    public synchronized void play() {
        remainingQuestions--;
    }

    public synchronized int getRemainingQuestions() {
        return remainingQuestions;
    }

    public synchronized void checkNullSetQuestion(Question randomQuestion) {
        if (question == null) {
            question = randomQuestion;
        }
    }

    public synchronized void next(Usr current) throws InterruptedException {
        if(current.getLogin().equals(player1.getLogin())) {
            player1answered = true;
        } else {
            player2answered = true;
        }
        if (!player1answered || !player2answered) {
            wait();
        }
        player1answered = player2answered = false;
        notifyAll();
    }

    public synchronized void verify(Usr user, String chosen) {
        if (user.getLogin().equals(player1.getLogin())) {
            correct1 += chosen.equals(question.getAnswer()) ? 1 : 0;
        }
        if (user.getLogin().equals(player2.getLogin())) {
            correct2 += chosen.equals(question.getAnswer()) ? 1 : 0;
        }
        MainApplication.logger.info("Correct1: " + correct1 + " Correct2: " + correct2);
    }

    // Used to break the monitors when the object is set for garbage collection.
    public synchronized void unblock() {
        player1answered = player2answered = true;
        notifyAll();
    }
}
