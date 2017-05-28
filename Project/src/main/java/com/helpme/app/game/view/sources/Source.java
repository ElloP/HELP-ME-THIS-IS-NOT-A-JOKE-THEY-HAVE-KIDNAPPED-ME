package com.helpme.app.game.view.sources;

import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_PITCH;

/**
 * Created by Jesper on 2017-04-14.
 */
public class Source {
    private int sourceId;
    private int buffer;

    public Source(int buffer){
        sourceId = AL10.alGenSources();
        setVolume(1);
        setPitch(1);
        this.buffer = buffer;
    }

    public void play() {
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
        AL10.alSourcePlay(sourceId);
    }

    public void delete(){
        AL10.alDeleteSources(sourceId);
    }

    public void setVolume(float volume) {

        AL10.alSourcef(sourceId, AL_GAIN, volume);
    }

    public void setPitch(float pitch) {
        AL10.alSourcef(sourceId, AL_PITCH, pitch);
    }

    public void setPosition(float x, float y, float z) {
        AL10.alSource3f(sourceId, AL10.AL_POSITION, x, y, z);
    }

    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }
}
