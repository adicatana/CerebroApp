package group15.cerebro.session.multi;

import group15.cerebro.entities.Usr;

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

    public synchronized Match match(Usr usr) throws InterruptedException {
        // should push this to the front-end
        // TODO: check for all possible scenarios
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
//        if (users.size() < 2) {
//            return null;
//        }

        match = new Match(getRemoveUser(usr), getFirstUser());
        matches.add(match);

        return match;
    }

    private synchronized Usr getRemoveUser(Usr usr) {
        for (Usr user : users) {
            if (user.getLogin().equals(usr.getLogin())) {
                users.remove(user);
                return user;
            }
        }

        // Should not get here
        return null;
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

    private synchronized Usr getFirstUser() {
        Usr user = users.get(0);
        users.remove(0);
        return user;
    }

    public synchronized void join(Usr newUser) {
        for (Usr user : users) {
            if (newUser.getLogin().equals(user.getLogin())) {
                return;
            }
        }
        users.add(newUser);
        notifyAll();
    }

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
        users.remove(user);
        notifyAll();
    }
}
