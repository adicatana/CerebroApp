package group15.cerebro.session.models;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.session.multi.QuestionRandomizer;
import group15.cerebro.session.templates.GameEngine;

public class Game implements GameEngine {
    private final static int GAMES = 5;

    private int games;
    private Question question;
    private boolean accept;
    private int countGood;

    public Game() {
        games = GAMES;
        countGood = 0;
        accept = true;
        question = null;
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
        return new QuestionRandomizer().randomizeOrder(question);
    }

    @Override
    public boolean respond(String response) {
        boolean good = response.equals(question.getAnswer());
        if (accept && good) {
            countGood++;
        }
        accept = false;

        MainApplication.logger.warn("Game: Good responses: " + countGood);

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
