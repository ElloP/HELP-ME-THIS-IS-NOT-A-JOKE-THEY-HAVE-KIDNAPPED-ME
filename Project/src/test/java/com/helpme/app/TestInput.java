package com.helpme.app;

/**
 * Created by og on 2017-04-20.
 */
import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;


public class TestInput {

    public static void main(String[]args){
        boolean hej = true;
        Window.initWindow(10,10,"tja");
        EngineCore ec = new EngineCore();
        ec.start();


        while(hej){
            if(Input.isKeyboardKeyDown(InputKey.MoveForward))
                hej = false;
        }
        System.out.println("inte hej!");
    }
}
