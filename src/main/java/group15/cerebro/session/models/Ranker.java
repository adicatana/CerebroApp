package group15.cerebro.session.models;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;

public class Ranker {
    private Usr user;
    private UserRepository repository;

    private final int MAX_DIFF = 10;
    private final int FACT = 3;
    private final int SCALE = 10000;
    private final int SEGM_SIZE = 1000;

    private int segment;

    public Ranker(Usr user, UserRepository repository) {
        this.user = user;
        if (user == null) {
            this.segment = 0;
        }
        else {
            this.segment = user.getRating() / SEGM_SIZE;
        }
        this.repository = repository;
    }

    public void update(boolean correct) {
        if (user == null || repository == null) {
            return;
        }
        int newRating = user.getRating();
        int oldRating = newRating;
        if (correct) {
            int gain = FACT * (MAX_DIFF - segment);
            newRating += gain;
        } else {
            int loss = FACT * segment / 2;
            newRating -= loss;
        }
        newRating = Math.max(0, newRating);
        newRating = Math.min(SCALE, newRating);
        user.setRating(newRating);

        repository.save(user);
        MainApplication.logger.warn("Rating update: old(" + oldRating
                +  "), new(" + newRating + ") for user " + user.getName());
    }

}
