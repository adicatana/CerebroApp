package group15.cerebro.session.templates;

import group15.cerebro.entities.Usr;

import java.util.UUID;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
public interface SessionManagerEngine {
    void makeNewSession(String auth);

    void startNewGame();

    void selectTopic();

    Phase getPhase();

    UUID getUid();

    void finishGame();

    Usr getUserForSession();

    default String getPhaseName(Phase phase) {
        switch (phase) {
            case NONE: return "NONE";
            case LOGGED: return "LOGGED";
            case TOPIC: return "TOPIC_SELECTION";
            case SINGLE: return "SINGLE";
            case FEEDBACK: return "FEEDBACK";
            case RANKINGS: return "RANKINGS";
            case PROFILE: return "PROFILE";
            case MULTI: return "MULTI";
            case MULTIEND: return "MULTIEND";
            case ADMIN: return "ADMIN";
            case ABOUT: return "ABOUT";
            default: return "";
        }
    }

    void finishFeedbackPhase();

    void enterRankings();

    void leaveRankings();

    void enterProfile();

    void leaveProfile();

    void multiplayerJoin();

    void endMultiplayerGame();

    void returnMainScreenMultiplayerGame();

    void lostConnection();

    String dumpManager();

    void enterAdmin();

    void exitAdmin();

    void enterAbout();

    void exitAbout();

    enum Phase {
        NONE,
        LOGGED,
        RANKINGS,
        TOPIC,
        SINGLE,
        PROFILE,
        FEEDBACK,
        MULTI,
        MULTIEND,
        ADMIN,
        ABOUT
    }
}
