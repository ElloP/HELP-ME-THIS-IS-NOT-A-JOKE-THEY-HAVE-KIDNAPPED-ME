package com.helpme.app.world.sounds;

import com.helpme.app.world.sounds.Source.Source;

/**
 * Created by Jesper on 2017-04-14.
 */
public interface ISoundVisitor {
    public void visit(Source source);
}
