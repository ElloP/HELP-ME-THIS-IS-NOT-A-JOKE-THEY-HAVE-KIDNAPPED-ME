package com.helpme.app.character;

import com.helpme.app.item.Item;
import com.helpme.app.tile.Tile;

import java.util.ArrayList;

/**
 * Created by kopa on 2017-03-30.
 */
public abstract class Character {
	protected int hp;
	protected String name;
	protected ArrayList<Item> items;
    //Becomes a two-way dependence
    protected Tile tile;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

    public abstract int attack(NPC npc);

	public abstract void talk();

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(int index){
        this.items.remove(index);
    }

    public Tile getTile(){
        return tile;
    }

    public void setTile(Tile tile){
        this.tile = tile;
    }


}
