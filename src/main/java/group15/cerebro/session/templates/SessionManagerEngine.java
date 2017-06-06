package group15.cerebro.session.templates;

import group15.cerebro.entities.Usr;

import java.util.UUID;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
public interface SessionManagerEngine {
    void makeNewSession(Usr auth);

    void startNewGame();

    Phase getPhase();

    UUID getUid();

    default String getPhaseName(Phase phase) {
        switch (phase) {
            case NONE: return "NONE";
            case LOGGED: return "LOGGED";
            case SINGLE: return "SINGLE";
            default: return "";
        }
    }

    enum Phase {
        NONE,
        LOGGED,
        SINGLE
    }
}
