package com.helpme.app.engine.audio;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.ALC_MAX_AUXILIARY_SENDS;

/**
 * Created by Jesper on 2017-04-21.
 * Class that sets up a lot of the groundwork for the audio using ALC so that it is possible to play sounds.
 */
public class AudioHandler {
    private static final String PATH = new File("").getAbsolutePath();

    private static List<Integer> buffers = new ArrayList<>();
    private static long device;

    private AudioHandler() {

    }

    public static void init() throws ExceptionInInitializerError {

        device = alcOpenDevice((ByteBuffer) null);
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

        if (!ALC10.alcMakeContextCurrent(newContext)) {
            throw new ExceptionInInitializerError("Failed to make context current");
        }

        AL.createCapabilities(deviceCaps);

    }

    public static void setListenerPos(float x, float y, float z) {
        AL10.alListener3f(AL10.AL_POSITION, x, y, z);
    }

    public static void setListenerVel(float x, float y, float z) {
        AL10.alListener3f(AL10.AL_VELOCITY, x, y, z);
    }

    public static int loadSound(String file) {
        int buffer = AL10.alGenBuffers();
        try {
            buffers.add(buffer);
            WaveData waveData = WaveData.create(new BufferedInputStream(new FileInputStream(PATH + file)));
            AL10.alBufferData(buffer, waveData.format, waveData.data, waveData.samplerate);
            waveData.dispose();
        } catch (IOException e) {
            System.err.print("IOException in AudioHandler.loadSound::");
            e.printStackTrace();
        }
        return buffer;
    }

    public static void cleanUp() {
        for (int buffer : buffers) {
            AL10.alDeleteBuffers(buffer);
        }
        ALC10.alcCloseDevice(device);
        ALC.destroy();
    }
}
