package com.helpme.app.engine.sounds;

import com.helpme.app.engine.sounds.sources.Source;

/**
 * Created by Jesper on 2017-04-14.
 */
public interface ISoundVisitor {
    public void visit(Source source);
}
