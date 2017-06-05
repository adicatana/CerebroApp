package group15.cerebro.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionManager {
    private Phase phase;

    public SessionManager() {
        this.phase = Phase.NONE;
    }

    public enum Phase {
        NONE,
        LOGGED,
        NAVIGATE,
        SINGLE
    }

    public void makeNewSession() {
        if (phase == Phase.NONE) {
            phase = Phase.LOGGED;
        }
    }

    public Phase getPhase() {
        return phase;
    }
}
