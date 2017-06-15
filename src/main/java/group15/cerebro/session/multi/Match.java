package group15.cerebro.session.multi;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Usr;

import java.util.Timer;
import java.util.TimerTask;

public class Match {
    private Usr player1;
    private Usr player2;
    private static final int TOTAL = 10;
    private int remainingQuestions;

    private int correct1;
    private int correct2;

    private boolean pinged1;
    private boolean pinged2;

    private TimerTask timerTask;
    private Timer timer;
    private Question question;

    public Match(Usr player1, Usr player2) {
        this.player1 = player1;
        this.player2 = player2;

        correct1 = 0;
        correct2 = 0;
        pinged1 = false;
        pinged2 = false;

        question = null;
        remainingQuestions = getTOTAL();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                pinged1 = false;
                pinged2 = false;
            }
        };

        timer = new Timer("timer");
        timer.scheduleAtFixedRate(timerTask, 30, 100);
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

    public synchronized boolean ping(Usr user) {
        if (user.getLogin().equals(player1.getLogin())) {
            pinged1 = true;
        }
        if (user.getLogin().equals(player2.getLogin())) {
            pinged2 = true;
        }
        return pinged1 && pinged2;
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

    private int ctr = 0;

    // Always 2.
    public synchronized void next() throws InterruptedException {
        ctr++;
        if (ctr < 2) {
            wait();
        }
        ctr = 0;
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
}
