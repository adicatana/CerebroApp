

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
    public void makeNewSession(String auth) {
        MainApplication.logger.warn("Session started for user:" + auth);
        if (phase == Phase.NONE) {
            uid = UUID.randomUUID();

            phase = Phase.LOGGED;

            user = loginChecker.findUser(auth);
            MainApplication.logger.warn("User name found in DB:" + user.getName());
        }
    }

    @Override
    public void startNewGame() {
        if (phase == Phase.TOPIC) {
            phase = Phase.SINGLE;
        }
    }

    @Override
    public void selectTopic() {
        if (phase == Phase.LOGGED) {
            phase = Phase.TOPIC;
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

    @Override
    public void finishFeedbackPhase() {
        if (phase == Phase.FEEDBACK) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public void enterRankings() {
        if (phase != Phase.NONE) {
            phase = Phase.RANKINGS;
        }
    }

    @Override
    public void leaveRankings() {
        if (phase == Phase.RANKINGS) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public void enterProfile() {
        if (phase != Phase.NONE) {
            phase = Phase.PROFILE;
        }
    }

    @Override
    public void multiplayerJoin() {
        if (phase == Phase.LOGGED) {
            phase = Phase.MULTI;
        }
    }

    @Override
    public void leaveProfile() {
        if (phase == Phase.PROFILE || phase == Phase.LOGGED
                || phase == Phase.RANKINGS || phase == Phase.TOPIC) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public void endMultiplayerGame() {
        if (phase == Phase.MULTI) {
            phase = Phase.MULTIEND;
        }
    }

    @Override
    public void returnMainScreenMultiplayerGame() {
        if (phase == Phase.MULTIEND || phase == Phase.MULTI) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public void lostConnection() {
        phase = Phase.LOGGED;
    }

    @Override
    public String dumpManager() {
        return "; user: " + (this.getUserForSession() == null ? "NULL" :this.getUserForSession().getName()) +
                ", phase: " + this.phase.name();
    }

    @Override
    public void enterAdmin() {
        if (phase != Phase.NONE) {
            phase = Phase.ADMIN;
        }
    }

    @Override
    public void exitAdmin() {
        if (phase == Phase.ADMIN) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public void enterAbout() {
        if (phase != Phase.NONE) {
            phase = Phase.ABOUT;
        }
    }

    @Override
    public void exitAbout() {
        if (phase == Phase.ABOUT) {
            phase = Phase.LOGGED;
        }
    }

    @Override
    public Usr getUserForSession() {
        return user;
    }
}
