package com.helpme.app.effecttest;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.concrete.Damage;
import com.helpme.app.game.model.item.effect.concrete.Heal;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-28.
 */
public class EffectTest {
    MockTarget mockTarget;

    @Before
    public void setup() {
        mockTarget = new MockTarget();
    }

    @Test
    public void testDamageApply() {
        IEffect damage = new Damage(3);
        damage.apply(mockTarget);
        assert (mockTarget.damage == 3);
    }

    @Test
    public void testHealApply() {
        IEffect heal = new Heal(6);
        heal.apply(mockTarget);
        assert (mockTarget.heal == 6);
    }

    @Test
    public void testHealEquals() {
        IEffect heal0 = new Heal(6);
        IEffect heal1 = new Heal(6);
        assert heal0.equals(heal1);
    }

    @Test
    public void testHealNotEquals() {
        IEffect heal0 = new Heal(6);
        IEffect heal1 = new Heal(5);
        assert !heal0.equals(heal1);
    }

    @Test
    public void testDamageEquals() {
        IEffect damage0 = new Damage(2);
        IEffect damage1 = new Damage(2);
        assert damage0.equals(damage1);
    }

    @Test
    public void testDamageNotEquals() {
        IEffect damage0 = new Damage(2);
        IEffect damage1 = new Damage(10);
        assert !damage0.equals(damage1);
    }
}
