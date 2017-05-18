package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;

/**
 * Created by kopa on 2017-05-16.
 */
public class Follow extends Behaviour {
    private int followingDistance;
    private Either<String, String> foundPlayer;
    private Either<String, String> lostPlayer;

    public Follow(int followingDistance, Either<String, String> foundPlayer, Either<String, String> lostPlayer){
        this.followingDistance = followingDistance;
        this.foundPlayer = foundPlayer;
        this.lostPlayer = lostPlayer;
    }

    @Override
    public String update(IReadBody body, IReadSurroundings surroundings) {
        if (foundPlayer(body, surroundings)) {
            return Maybe.wrap(foundPlayer);
        } else if (lostPlayer(body, surroundings)) {
            return Maybe.wrap(lostPlayer);
        } else {
            return new Nothing();
        }
    }

    private boolean foundPlayer(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(p -> Intelligence.isMonsterFacing(body, p.readPosition()));
    }

    private boolean lostPlayer(IReadBody body, IReadSurroundings surroundings){
        return !(surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c < followingDistance)
                && !isInRange(body, surroundings);
    }

    private boolean isInRange(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(player -> {
            int cost = surroundings.getShortestPath(body.readPosition(), player.readPosition()).c;
            return cost > 0 && cost <= followingDistance;
        });
    }
}
