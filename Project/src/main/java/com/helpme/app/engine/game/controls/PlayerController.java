package com.helpme.app.engine.game.controls;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Lerper;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-15.
 */
public class PlayerController extends CameraController{
    private Lerper lerper;

    private boolean moving = false;
    private boolean rotating = false;

    private float rotationDuration = 1f;
    private float movementDuration = 1f;

    private final float moveLength = 6.0f;

    public PlayerController(Camera camera, Time time) {
        super(camera, time);
    }

    public PlayerController(Camera camera, Time time, float rotationDuration, float movementDuration) {
        this(camera, time);
        this.rotationDuration = rotationDuration;
        this.movementDuration = movementDuration;
    }

    private void lerpMove(Vector3f direction) {
        Vector3f startPos = getCamera().getPosition();
        Vector3f endPos = getCamera().getPosition().add(direction.multiply(moveLength)); //Note(Olle): move camera 6 units forward

        lerper = new Lerper(startPos, endPos, getTime(), movementDuration);
        moving = true;
    }

    private void rotate(Vector3f dir) {
        Vector3f camPos = getCamera().getPosition();
        Vector3f startPos = new Vector3f(getCamera().getForward().add(camPos));
        Vector3f endPos = new Vector3f(dir.add(camPos));

        lerper = new Lerper(startPos, endPos, getTime(), rotationDuration);
        rotating = true;
    }

    public void rotateRight() {
        rotate(getCamera().getRight());
    }

    public void rotateLeft() {
        rotate(getCamera().getLeft());
    }

    public void moveForward() {
        lerpMove(getCamera().getForward());
    }

    public void moveBack() {
        lerpMove(getCamera().getBack());
    }

    public void moveLeft() {
        lerpMove(getCamera().getLeft());
    }

    public void moveRight() {
        lerpMove(getCamera().getRight());
    }

    private boolean finished() {
        return lerper == null || lerper.finished();
    }

    public void update() {
        if(!finished()) {
            if(moving) {
                getCamera().setPosition(lerper.lerp());
            } else if(rotating) {
                getCamera().lookAt(lerper.lerp());
            }
        } else {
            moving = false;
            rotating = false;

            if (Input.isKeyboardKeyPress(InputKey.MoveForward)) {
                setChanged();
                notifyObservers(GameEvent.MOV_FORWARD);
                //moveForward();
            }
            if (Input.isKeyboardKeyPress(InputKey.MoveLeft)) {
                setChanged();
                notifyObservers(GameEvent.MOV_LEFT);
                //moveLeft();
            }
            if (Input.isKeyboardKeyPress(InputKey.MoveRight)) {
                setChanged();
                notifyObservers(GameEvent.MOV_RIGHT);
                //moveRight();
            }
            if (Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
                setChanged();
                notifyObservers(GameEvent.MOV_BACK);
                //moveBack();
            }
            if (Input.isKeyboardKeyPress(InputKey.RotateLeft)) {
                setChanged();
                notifyObservers(GameEvent.ROT_LEFT);
                //rotateLeft();
            }
            if (Input.isKeyboardKeyPress(InputKey.RotateRight)) {
                setChanged();
                notifyObservers(GameEvent.ROT_RIGHT);
                //rotateRight();
            }
        }
    }
}
