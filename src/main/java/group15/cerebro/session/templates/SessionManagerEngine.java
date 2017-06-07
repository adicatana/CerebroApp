package group15.cerebro.session.templates;

import java.util.UUID;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
public interface SessionManagerEngine {
    void makeNewSession(String auth);

    void startNewGame();

    Phase getPhase();

    UUID getUid();

    void finishGame();

    default String getPhaseName(Phase phase) {
        switch (phase) {
            case NONE: return "NONE";
            case LOGGED: return "LOGGED";
            case SINGLE: return "SINGLE";
            case FEEDBACK: return "FEEDBACK";
            default: return "";
        }
    }

    void finishFeedbackPhase();

    enum Phase {
        NONE,
        LOGGED,
        SINGLE,
        FEEDBACK
    }
}
