package group15.cerebro.session.templates;

import group15.cerebro.entities.Question;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
public interface GameEngine {

    int getGames();

    void play();

    void setQuestion(Question question);

    String getAnswer();

    Question genRandomOrder();

    boolean respond(String response);

    int getCountGood();

    Question getQuestion();

    int getPercent();
}
