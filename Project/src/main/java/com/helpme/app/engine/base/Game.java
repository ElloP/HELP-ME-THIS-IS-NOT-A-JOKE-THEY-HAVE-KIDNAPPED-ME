package com.helpme.app.engine.base;

import com.helpme.app.engine.base.test.MockWorld;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.utils.Vector2f;
import com.sun.org.apache.xpath.internal.SourceTree;


/**
 * Authored by Olle on 2017-04-05.
 */
public class Game {
    MockWorld mockWorld;
    public Game() {
        mockWorld = new MockWorld();
    }

    public void input() {
        testControlls();
    }


    public void update() {

    }

    public void draw() {

    }


    private void testControlls(){
        if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
            Vector2f prevPos = mockWorld.playerController.getPlayer().getPosition();
            mockWorld.playerController.movePlayerForward();
            Vector2f currPos = mockWorld.playerController.getPlayer().getPosition();
            if(prevPos.equals(currPos)) {
                System.out.println("Cannot move forward.");
            }else {
                System.out.println("Player moved from " + prevPos.toString() + " to " + currPos.toString());
            }
        }
        if(Input.isKeyboardKeyPress(InputKey.MoveBackward)){
            Vector2f prevPos = mockWorld.playerController.getPlayer().getPosition();
            mockWorld.playerController.movePlayerBackward();
            Vector2f currPos = mockWorld.playerController.getPlayer().getPosition();
            if(prevPos.equals(currPos)) {
                System.out.println("Cannot move backward.");
            }else {
                System.out.println("Player moved from " + prevPos.toString() + " to " + currPos.toString());
            }
        }
        if(Input.isKeyboardKeyPress(InputKey.MoveRight)){
            Vector2f prevPos = mockWorld.playerController.getPlayer().getPosition();
            mockWorld.playerController.movePlayerRight();
            Vector2f currPos = mockWorld.playerController.getPlayer().getPosition();
            if(prevPos.equals(currPos)) {
                System.out.println("Cannot move right.");
            }else {
                System.out.println("Player moved from " + prevPos.toString() + " to " + currPos.toString());
            }
        }
        if(Input.isKeyboardKeyPress(InputKey.MoveLeft)){
            Vector2f prevPos = mockWorld.playerController.getPlayer().getPosition();
            mockWorld.playerController.movePlayerLeft();
            Vector2f currPos = mockWorld.playerController.getPlayer().getPosition();
            if(prevPos.equals(currPos)) {
                System.out.println("Cannot move left.");
            }else {
                System.out.println("Player moved from " + prevPos.toString() + " to " + currPos.toString());
            }
        }
        if(Input.isKeyboardKeyPress(InputKey.RotateLeft)){
            mockWorld.playerController.rotatePlayerLeft();
        }
        if(Input.isKeyboardKeyPress(InputKey.RotateRight)){
            mockWorld.playerController.rotatePlayerRight();
        }
        if(Input.isKeyboardKeyPress(InputKey.Attack)){
            
            mockWorld.playerController.usePlayerAttack();
        }
        if(Input.isKeyboardKeyPress(InputKey.Selfie)){

        }
    }
 /*
    private void testingCameraInput() {
        float movAmt = (float) (10 * Time.deltaTime);
        float rotAmt = (float) (10 * Time.deltaTime);

        if(Input.isKeyboardKeyDown(InputKey.MoveForward))
            c.moveForward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveLeft))
            c.moveLeft(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveRight))
            c.moveRight(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveBackward))
            c.moveBackward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.RotateLeft))
            c.rotate(0.0f, rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.RotateRight))
            c.rotate(0.0f, -rotAmt, 0.0f);
    }
    */


}
