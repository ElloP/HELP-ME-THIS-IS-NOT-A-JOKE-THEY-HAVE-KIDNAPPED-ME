package com.helpme.app.item;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IKeyFactory {
    static IKey redKey() {
        return new Key("Red Key");
    }

    static IKey skeletonKey() {
        return new Key("Skeleton Key");
    }
}
