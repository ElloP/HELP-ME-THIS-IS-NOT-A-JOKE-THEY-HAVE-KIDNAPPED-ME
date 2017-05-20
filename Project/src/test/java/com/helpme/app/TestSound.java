package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.engine.sounds.sources.AbstractMonsterSource;
import com.helpme.app.engine.sounds.audio.AudioHandler;
import com.helpme.app.engine.sounds.AudioObserver;
import com.helpme.app.engine.sounds.sources.PlayerSource;
import com.helpme.app.engine.sounds.sources.MonsterSource;
import com.helpme.app.engine.sounds.sources.Source;

import java.util.ArrayList;

/**
 * Created by Jesper on 2017-04-21.
 */
public class TestSound {
    private MockWorld1 mockWorld;


    public TestSound(){
        this.mockWorld = new MockWorld1();
    }

    public static void main(String[] args) throws Exception {
        TestSound testSound = new TestSound();
        Body player = (Body) testSound.mockWorld.player.readBody();

        AudioHandler.init();
        AudioHandler.setListenerPos(player.readPosition().x, player.readPosition().y, 0);

        int buffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\world\\sounds\\Cowboy.wav");
        Body body0 = (Body) testSound.mockWorld.enemyConsciousness0.readBody();
        MonsterSource monsterSource1 = new MonsterSource(body0, new Source(), buffer, -1, -1, body0.readPosition().x, body0.readPosition().y, 0);
        PlayerSource playerSource = new PlayerSource(player, new Source(), buffer, -1, -1, player.readPosition().x, player.readPosition().y, 0);
        ArrayList<AbstractMonsterSource> monsterSources = new ArrayList<>();
        monsterSources.add(playerSource);
        monsterSources.add(monsterSource1);
        AudioObserver audioObserver = new AudioObserver(monsterSources);
        player.addObserver(audioObserver);
        body0.addObserver(audioObserver);


        AudioHandler.cleanUp();
    }

}
