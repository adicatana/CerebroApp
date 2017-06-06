

package group15.cerebro.session;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("session")
public class SessionManager {
    private Phase phase;
    private UUID uid;
    private Usr user;

    private LoginChecker loginChecker;

    @Autowired
    public SessionManager(UserRepository userRepository) {
        this.phase = Phase.NONE;
        this.loginChecker = new LoginChecker(userRepository);
    }

    public enum Phase {
        NONE,
        LOGGED,
        SINGLE
    }

    public void makeNewSession(Usr auth) {
        MainApplication.logger.warn(auth.getLogin());
        if (phase == Phase.NONE && loginChecker.validateAuthetication(auth)) {
            uid = UUID.randomUUID();

            phase = Phase.LOGGED;
            user = loginChecker.getUser();
        }
    }

    public void randomQuestion() {

    }

    public void startNewGame() {
        if (phase == Phase.LOGGED) {
            phase = Phase.SINGLE;
        }
    }

    public Phase getPhase() {
        return phase;
    }

    public UUID getUid() {
        return uid;
    }
}
