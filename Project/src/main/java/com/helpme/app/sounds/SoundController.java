package com.helpme.app.sounds;

import com.helpme.app.character.Monster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.IController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-14.
 */
public class SoundController implements IController, Observer {
    private Monster player;
    private ISoundVisitor permanentVisitor;
    private WalkingSound walkingSound = new WalkingSound("", "");

    public SoundController(Monster player, ISoundVisitor permanentVisitor){
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
        if (!Vector2f.equals(temp.getPosition(), player.getPosition())){
            accept(permanentVisitor, walkingSound);
        }
    }
}
