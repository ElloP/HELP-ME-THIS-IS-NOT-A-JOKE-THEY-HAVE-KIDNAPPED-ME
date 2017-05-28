package com.helpme.app.game.saveload.item;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.Single;
import com.helpme.app.game.saveload.item.effect.EffectWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class SingleWrapper implements ILoadable<IItem> {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "attack")
    private EffectWrapper attackWrapper;

    @XmlElement(name = "selfie")
    private EffectWrapper selfieWrapper;

    public SingleWrapper(){

    }

    public SingleWrapper(Single single){
        this.name = single.readName();
        this.attackWrapper = new EffectWrapper(single.getAttackEffect());
        this.selfieWrapper = new EffectWrapper(single.getSelfieEffect());
    }

    @Override
    public IItem getObject() {
        return new Single(name, attackWrapper.getObject(), selfieWrapper.getObject());
    }
}
