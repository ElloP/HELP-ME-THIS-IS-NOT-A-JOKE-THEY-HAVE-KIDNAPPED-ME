package com.helpme.app.game.saveload.item.effect;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.concrete.EffectFactory;
import com.helpme.app.game.model.item.effect.concrete.Heal;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class HealWrapper implements ILoadable<IEffect> {
    @XmlElement(name = "amount")
    private float amount;

    public HealWrapper(){

    }

    public HealWrapper(Heal heal){
        this.amount = heal.getAmount();
    }

    @Override
    public IEffect getObject() {
        return EffectFactory.createHeal(amount);
    }
}
