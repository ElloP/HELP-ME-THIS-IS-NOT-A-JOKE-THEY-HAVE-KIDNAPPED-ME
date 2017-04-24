package com.helpme.app.world.sounds;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.*;
import org.lwjgl.openal.ALCCapabilities.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.ALC_MAX_AUXILIARY_SENDS;

/**
 * Created by Jesper on 2017-04-21.
 */
public class AudioHandler {
    private static List<Integer> buffers = new ArrayList<>();
    private static long device;

    public static void init() throws Exception {

        device = alcOpenDevice((ByteBuffer)null);
        ALCCapabilities deviceCaps = ALC.createCapabilities(device);
        IntBuffer contextAttribList = BufferUtils.createIntBuffer(16);

        contextAttribList.put(ALC_REFRESH);
        contextAttribList.put(60);

        contextAttribList.put(ALC_SYNC);
        contextAttribList.put(ALC_FALSE);

        contextAttribList.put(ALC_MAX_AUXILIARY_SENDS);
        contextAttribList.put(2);

        contextAttribList.put(0);
        contextAttribList.flip();

        long newContext = ALC10.alcCreateContext(device, contextAttribList);

        if(!ALC10.alcMakeContextCurrent(newContext)) {
            throw new Exception("Failed to make context current");
        }

        AL.createCapabilities(deviceCaps);

    }

    public static void setListenerPos(float x, float y, float z) {
        AL10.alListener3f(AL10.AL_POSITION, x, y, z);
    }

    public static void setListenerVel(float x, float y, float z) {
        AL10.alListener3f(AL10.AL_VELOCITY, x, y, z);
    }
    public static int loadSound(String file) throws FileNotFoundException {
        int buffer = AL10.alGenBuffers();
        buffers.add(buffer);
        WaveData waveData = WaveData.create(new BufferedInputStream(new FileInputStream(file)));
        AL10.alBufferData(buffer, waveData.format, waveData.data, waveData.samplerate);
        waveData.dispose();
        return buffer;
    }

    public static void cleanUp() {
        for (int buffer : buffers){
            AL10.alDeleteBuffers(buffer);
        }
        ALC.destroy();
        //ALC10.alcCloseDevice(device);
    }
}
