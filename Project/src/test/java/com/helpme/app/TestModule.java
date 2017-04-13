package com.helpme.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.helpme.app.character.IInventory;
import com.helpme.app.character.IMonster;
import com.helpme.app.character.Inventory;
import com.helpme.app.character.Monster;
import com.helpme.app.item.*;
import com.helpme.app.item.effect.IEffectFactory;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.ILevel;
import com.helpme.app.world.Level;
import com.helpme.app.world.PlayerController;

//import com.google.inject.AbstractModule;

/**
 * Created by Jesper on 2017-04-12.
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IItem.class).to(Item.class);
    }

    //@Provides
    //IItem mockupItem(@Named(""))
}
