package com.helpme.app;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.target.ITarget;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-11.
 */
public class ConsciousnessTest {
    /*
    @Test
    public void testBlockedByMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        Vector2f tileTo = new Vector2f(2, 3);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerRight();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerLeft();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testWalkOverDeadBodies(){
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerForward();
        Vector2f monsterPos = new Vector2f(Vector2f.add(tileStart,mockWorld.player.getPlayer().readDirection()));
        //assert (monsterPos.equals(new Vector2f(2,2)));
        while(!mockWorld.level.readBody(monsterPos).check(ITarget::isDead)){
            mockWorld.player.usePlayerAttack();
        }
        mockWorld.player.movePlayerForward();
        assert (mockWorld.player.getPlayer().readPosition().equals(monsterPos));
    }*/
}
