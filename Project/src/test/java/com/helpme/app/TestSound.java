package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.world.character.Monster;
import com.helpme.app.world.sounds.Source.AbstractMonsterSource;
import com.helpme.app.world.sounds.AudioHandler;
import com.helpme.app.world.sounds.AudioObserver;
import com.helpme.app.world.sounds.Source.PlayerSource;
import com.helpme.app.world.sounds.Source.MonsterSource;
import com.helpme.app.world.sounds.Source.Source;
import org.junit.Before;
import org.junit.Test;

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
        Monster player = (Monster) testSound.mockWorld.playerHandler.getPlayer();

        AudioHandler.init();
        AudioHandler.setListenerPos(player.readPosition().x, player.readPosition().y, 0);

        int buffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\world\\sounds\\Cowboy.wav");
        Monster monster0 = (Monster) testSound.mockWorld.enemyHandler0.getMonster();
        MonsterSource monsterSource1 = new MonsterSource(monster0, new Source(), buffer, -1, -1, monster0.readPosition().x, monster0.readPosition().y, 0);
        PlayerSource playerSource = new PlayerSource(player, new Source(), buffer, -1, -1, player.readPosition().x, player.readPosition().y, 0);
        ArrayList<AbstractMonsterSource> monsterSources = new ArrayList<>();
        monsterSources.add(playerSource);
        monsterSources.add(monsterSource1);
        AudioObserver audioObserver = new AudioObserver(monsterSources);
        player.addObserver(audioObserver);
        monster0.addObserver(audioObserver);


        AudioHandler.cleanUp();
    }

}
