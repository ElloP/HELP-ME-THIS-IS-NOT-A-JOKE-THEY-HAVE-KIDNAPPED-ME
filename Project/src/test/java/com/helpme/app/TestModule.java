package com.helpme.app;

import com.google.inject.AbstractModule;
import com.helpme.app.item.*;

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
