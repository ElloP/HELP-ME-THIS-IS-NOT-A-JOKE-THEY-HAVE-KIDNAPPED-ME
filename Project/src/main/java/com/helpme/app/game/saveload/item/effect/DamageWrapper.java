package com.helpme.app.game.saveload.item.effect;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.concrete.Damage;
import com.helpme.app.game.model.item.effect.concrete.EffectFactory;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class DamageWrapper implements ILoadable<IEffect> {
    @XmlElement(name = "amount")
    private float amount;

    public DamageWrapper(){

    }

    public DamageWrapper(Damage damage){
        amount = damage.getAmount();
    }

    @Override
    public IEffect getObject() {
        return EffectFactory.createDamage(amount);
    }
}
