package group15.cerebro.session.multi;

import group15.cerebro.entities.Usr;

import java.util.ArrayList;
import java.util.List;

public class SessionPool {
    private List<Usr> users;
    private List<Match> matches;

    public SessionPool() {
        matches = new ArrayList<>();
        users = new ArrayList<>();
    }

    public synchronized Match match(Usr usr) throws InterruptedException {
        // should push this to the front-end
        while (users.size() < 2) {
            wait();
        }

        Match match = tryMatch(usr);
        if (match != null) {
            return match;
        }
//        if (users.size() < 2) {
//            return null;
//        }

        match = new Match(getRemoveUser(usr), getFirstUser());
        matches.add(match);

        return match;
    }

    private Usr getRemoveUser(Usr usr) {
        for (Usr user : users) {
            if (user.getLogin().equals(usr.getLogin())) {
                users.remove(user);
                return user;
            }
        }

        // Should not get here
        return null;
    }

    private Match tryMatch(Usr usr) {
        for (Match tryMatch : matches) {
            if (tryMatch.getPlayer1().getLogin().equals(usr.getLogin())
                    || tryMatch.getPlayer2().getLogin().equals(usr.getLogin())) {
                matches.remove(tryMatch);
                return tryMatch;
            }
        }
        return null;
    }

    private Usr getFirstUser() {
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
}
