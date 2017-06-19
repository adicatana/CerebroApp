package group15.cerebro.session.multi;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;

public class SessionPool {

    private List<Usr> users;
    private List<Match> matches;
    private List<Usr> dismissed;

    public SessionPool() {
        matches = new ArrayList<>();
        users = new ArrayList<>();
        dismissed = new ArrayList<>();
    }

    public synchronized void join(Usr newUser) {
        if (users.contains(newUser)) {
            /* Frontend sent two requests for same user. */
            return;
        }
        users.add(newUser);
    }

    private synchronized Match tryMatch(Usr usr) {
        for (Match tryMatch : matches) {
            if (tryMatch.getPlayer1().getLogin().equals(usr.getLogin())
                    || tryMatch.getPlayer2().getLogin().equals(usr.getLogin())) {
                matches.remove(tryMatch);
                return tryMatch;
            }
        }
        return null;
    }

    /**
     * Creates match or finds an already created one.
     */
    public synchronized Match match(Usr usr) throws InterruptedException {
        Match match = tryMatch(usr);
        if (match != null) {
            return match;
        }
        while (users.size() < 2) {
            wait();

            // Abrupt exit code -- see dismiss
            if (checkDismissed(usr)) {
                return null;
            }

            // Trying to solve matcher
            match = tryMatch(usr);

            if (match != null) {
                return match;
            }
        }
        getRemoveUser(usr);
        Usr usr2 = getFirstUser();
        match = new Match(usr, usr2);
        matches.add(match);
        notifyAll();
        return match;
    }

    /* Removes user usr from users list and returns it. */
    private synchronized void getRemoveUser(Usr usr) {
        if (users.contains(usr)) {
            users.remove(usr);
            return;
        }

        // Should not get here
        MainApplication.logger.warn("User not found.");
    }


    private synchronized Usr getFirstUser() {
        if (users.isEmpty()) {
            MainApplication.logger.warn("User list empty. Strange behaviour with users list.");
        }
        return users.remove(0);
    }

    // Clean references for grabage collection.
    private synchronized boolean checkDismissed(Usr user) {
        for (Usr check : dismissed) {
            if (user.getLogin().equals(check.getLogin())) {
                dismissed.remove(user);
                return true;
            }
        }
        return false;
    }

    public synchronized void dismiss(Usr user) {
        dismissed.add(user);
        if (users.contains(user)) {
            // I did not entered in a match
            users.remove(user);
        } else {
            // I entered in a match. So I exit during the game.
            // HANDLED in exit-room in MultiplayerController
        }
        notifyAll();
    }
}
