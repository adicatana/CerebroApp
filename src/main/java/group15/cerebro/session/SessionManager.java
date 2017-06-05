

package group15.cerebro.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("session")
public class SessionManager {
    private Phase phase;
    private UUID uid;

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
        uid = UUID.randomUUID();
    }

    public Phase getPhase() {
        return phase;
    }

    public UUID getUid() {
        return uid;
    }
}
