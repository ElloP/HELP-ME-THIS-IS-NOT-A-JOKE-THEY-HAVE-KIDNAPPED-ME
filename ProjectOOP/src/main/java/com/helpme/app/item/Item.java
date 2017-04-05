package com.helpme.app.item;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return name == ((Item) o).name;
    }
}
