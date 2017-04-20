package com.helpme.app.world.item;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IKeyFactory {
    static IItem redKey() {
        return new Key("Red Key");
    }

    static IItem skeletonKey() {
        return new Key("Skeleton Key");
    }
}
