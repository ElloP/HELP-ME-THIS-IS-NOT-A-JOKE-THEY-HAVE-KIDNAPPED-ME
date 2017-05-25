package com.helpme.app.game.view.camera;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Time;
import com.helpme.app.game.model.body.IReadBody;

/**
 * Created by kopa on 2017-05-25.
 */
public final class CameraViewFactory {
    private CameraViewFactory(){

    }

    public static PlayerCameraView createPlayerCameraView(IReadBody player, Time time){
        ICamera camera = new Camera(player.readPosition(), player.readDirection());
        return new PlayerCameraView(camera, time);
    }
}
