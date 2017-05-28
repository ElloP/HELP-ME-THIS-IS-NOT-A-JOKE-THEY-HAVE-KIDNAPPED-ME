package com.helpme.app.game.saveload.item;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.Consumable;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.model.item.concrete.Key;
import com.helpme.app.game.model.item.concrete.Single;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Klas on 2017-04-29.
 */
@XmlRootElement(name = "Item")
public class ItemWrapper implements ILoadable<Maybe<IItem>> {
    @XmlElement(name = "consumable")
    private ConsumableWrapper consumable;

    @XmlElement(name = "single")
    private SingleWrapper single;

    @XmlElement(name = "key")
    private KeyWrapper key;

    public ItemWrapper() {
    }

    public ItemWrapper(Maybe<IItem> maybeItem) {
        maybeItem.run(item -> {
            if (item instanceof Consumable) {
                consumable = new ConsumableWrapper((Consumable) item);
            } else if (item instanceof Single) {
                single = new SingleWrapper((Single) item);
            } else if (item instanceof Key) {
                key = new KeyWrapper((Key) item);
            }
        });
    }

    @Override
    public Maybe<IItem> getObject() {
        IItem item = null;

        if (consumable != null) {
            item = consumable.getObject();
        } else if (single != null) {
            item = single.getObject();
        } else if (key != null) {
            item = key.getObject();
        }

        return Maybe.wrap(item);
    }
}
