

package group15.cerebro.session.models;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.LoginChecker;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("session")
public class SessionManager implements SessionManagerEngine {
    private Phase phase;
    private UUID uid;
    private Usr user;

    private LoginChecker loginChecker;

    @Autowired
    public SessionManager(UserRepository userRepository) {
        this.phase = Phase.NONE;
        this.loginChecker = new LoginChecker(userRepository);
    }

    @Override
    public void makeNewSession(Usr auth) {
        MainApplication.logger.warn(auth.getLogin());
        if (phase == Phase.NONE && loginChecker.validateAuthetication(auth)) {
            uid = UUID.randomUUID();

            phase = Phase.LOGGED;
            user = loginChecker.getUser();
        }
    }

    @Override
    public void startNewGame() {
        if (phase == Phase.LOGGED) {
            phase = Phase.SINGLE;
        }
    }

    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public UUID getUid() {
        return uid;
    }

    @Override
    public void finishGame() {
        if (phase == Phase.SINGLE) {
            phase = Phase.FEEDBACK;
        }
    }
}
