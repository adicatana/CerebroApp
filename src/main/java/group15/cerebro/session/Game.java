package group15.cerebro.session;

import group15.cerebro.entities.Question;

import java.util.concurrent.ThreadLocalRandom;

public class Game implements GameEngine {
    private final static int GAMES = 5;
    private int games;
    private Question question;
    private boolean accept;
    private int countGood;

    public Game() {
        games = GAMES;
        countGood = 0;
    }

    @Override
    public int getGames() {
        return games;
    }

    @Override
    public void play() {
        games -= 1;
    }

    @Override
    public void setQuestion(Question question) {
        accept = true;
        this.question = question;
    }

    @Override
    public String getAnswer() {
        return question.getAnswer();
    }

    @Override
    public Question genRandomOrder() {
        int index = ThreadLocalRandom.current().nextInt(0, 6) + 1;
        Question out = new Question();
        out.setQuestion(question.getQuestion());
        if (index == 1) {
            out.setAnswer(question.getAnswer());
            out.setWrong1(question.getWrong1());
            out.setWrong2(question.getWrong2());
        }
        if (index == 2) {
            out.setAnswer(question.getAnswer());
            out.setWrong1(question.getWrong2());
            out.setWrong2(question.getWrong1());
        }
        if (index == 3) {
            out.setAnswer(question.getWrong1());
            out.setWrong1(question.getAnswer());
            out.setWrong2(question.getWrong2());
        }
        if (index == 4) {
            out.setAnswer(question.getWrong1());
            out.setWrong1(question.getWrong2());
            out.setWrong2(question.getAnswer());
        }
        if (index == 5) {
            out.setAnswer(question.getWrong2());
            out.setWrong1(question.getAnswer());
            out.setWrong2(question.getWrong1());
        }
        if (index == 6) {
            out.setAnswer(question.getWrong2());
            out.setWrong1(question.getWrong1());
            out.setWrong2(question.getAnswer());
        }
        return out;
    }

    @Override
    public boolean respond(String response) {
        boolean good = response.equals(question.getAnswer());
        if (accept && good) {
            countGood++;
        }
        accept = false;
        return good;
    }

    @Override
    public int getCountGood() {
        return countGood;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public int getPercent() {
        return (countGood * 100) / GAMES;
    }
}
