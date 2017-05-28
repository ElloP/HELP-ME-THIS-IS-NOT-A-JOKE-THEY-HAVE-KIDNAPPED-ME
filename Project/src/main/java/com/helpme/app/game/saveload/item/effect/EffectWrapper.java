package com.helpme.app.game.saveload.item.effect;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.concrete.Damage;
import com.helpme.app.game.model.item.effect.concrete.EffectFactory;
import com.helpme.app.game.model.item.effect.concrete.Heal;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class EffectWrapper implements ILoadable<IEffect> {
    @XmlElement(name = "damage")
    private DamageWrapper damageWrapper;

    @XmlElement(name = "heal")
    private HealWrapper healWrapper;

    public EffectWrapper() {

    }

    public EffectWrapper(IEffect effect) {
        if (effect instanceof Damage) {
            damageWrapper = new DamageWrapper((Damage) effect);
        } else if (effect instanceof Heal) {
            healWrapper = new HealWrapper((Heal) effect);
        }
    }

    @Override
    public IEffect getObject() {
        if (damageWrapper != null) {
            return damageWrapper.getObject();
        } else if (healWrapper != null) {
            return healWrapper.getObject();
        } else {
            return EffectFactory.createEmpty();
        }
    }
}
