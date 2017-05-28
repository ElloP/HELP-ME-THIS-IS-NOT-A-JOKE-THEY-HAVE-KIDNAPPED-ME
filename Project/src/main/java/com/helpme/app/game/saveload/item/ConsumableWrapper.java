package com.helpme.app.game.saveload.item;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.Consumable;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.saveload.item.effect.EffectWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class ConsumableWrapper implements ILoadable<IItem> {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "stacks")
    private int stacks;

    @XmlElement(name = "attack")
    private EffectWrapper attackWrapper;

    @XmlElement(name = "selfie")
    private EffectWrapper selfieWrapper;

    public ConsumableWrapper(){

    }

    public ConsumableWrapper(Consumable consumable){
        name = consumable.readName();
        stacks = consumable.getStacks();
        attackWrapper = new EffectWrapper(consumable.getAttackEffect());
        selfieWrapper = new EffectWrapper(consumable.getSelfieEffect());
    }


    @Override
    public IItem getObject() {
        return ItemFactory.createConsumable(name, stacks, attackWrapper.getObject(), selfieWrapper.getObject());
    }
}
