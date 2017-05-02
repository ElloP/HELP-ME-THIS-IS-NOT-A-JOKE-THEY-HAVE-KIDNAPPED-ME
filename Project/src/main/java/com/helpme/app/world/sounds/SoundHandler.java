package com.helpme.app.world.sounds;

import com.helpme.app.world.character.Monster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.handler.IHandler;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-14.
 */
public class SoundHandler implements IHandler, Observer {
    private Monster player;
    private ISoundVisitor permanentVisitor;
    private WalkingSound walkingSound = new WalkingSound("", "");

    public SoundHandler(Monster player, ISoundVisitor permanentVisitor){
        this.player = player;
        this.permanentVisitor = permanentVisitor;
        //player.addObserver(this);
    }

    @Override
    public void update() {

    }

    public void  accept (ISoundVisitor visitor, Sound sound){
        visitor.visit(sound);
    }

    @Override
    public void update(Observable o, Object arg) {
        Monster temp = (Monster) o;
        if (!Vector2f.equals(temp.readPosition(), player.readPosition())){
            accept(permanentVisitor, walkingSound);
        }
    }
}
