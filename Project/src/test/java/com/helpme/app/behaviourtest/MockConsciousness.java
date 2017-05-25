package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockConsciousness implements IConsciousness {
    int attacked;
    int movedForward;
    int rotatedLeft;
    int rotatedRight;

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void deleteObserver(Observer o) {

    }

    @Override
    public void update() {

    }

    @Override
    public void moveForward() {
        movedForward++;
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveBackward() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void rotateRight() {
        rotatedRight++;
    }

    @Override
    public void rotateLeft() {
        rotatedLeft++;
    }

    @Override
    public void setPosition(Vector2f position) {

    }

    @Override
    public void useAttack() {
        attacked++;
    }

    @Override
    public void usePickupAll() {

    }

    @Override
    public void usePickupSingle(int index) {

    }

    @Override
    public void setItems(IItem[] items) {

    }

    @Override
    public void dropItem(int index) {

    }

    @Override
    public void useSelfie() {

    }

    @Override
    public void changeActiveItem(int index) {

    }

    @Override
    public IBody getBody() {
        return null;
    }

    @Override
    public IReadBody readBody() {
        return null;
    }

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk() {
        return null;
    }

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk(int dialogueSelect) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
