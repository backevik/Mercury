package gui;

import java.util.List;

import gui.ZButton;
import gui.ZContainer;
import core.Drawable;
import core.EventAdder;
import core.GlobalStateManager;
import player.Playable;
import combat.Enemy;
import core.MouseObject;
import database.GameDataManager;

/**
 * Combat is a subclass of JFrame for re-rendering the current JFrame
 * to this GUI if combat occurs.
 *
 * @author Andreas Bäckevik
 * @version 0.1
 * @since 2015-01-30
 */

public class CombatViewer extends ZContainer implements Drawable
{
	@SuppressWarnings("unused")
	private ZButton atkbtn;
	@SuppressWarnings("unused")
	private ZButton spellbtn;
	@SuppressWarnings("unused")
	private ZButton itembtn;
	@SuppressWarnings("unused")
	private ZButton retreatbtn;
	
	private Playable player;
	private Enemy enemy;
		
	/*
	 * The constructor of Combat creates all the necessary components and adding relevant player spells.
	 * It also sets actionListeners to work with the game-logic.
	 */
	public CombatViewer (List<MouseObject> mouseObjects, EventAdder eventAdder, Playable players, Enemy enemies) {
		super(eventAdder,GameDataManager.getInstance().getImage("bgDefault.jpg"), 0, 0, mouseObjects);
		this.player = players;
		this.enemy = enemies;
		
		GlobalStateManager.getInstance().updateCurrentState("InCombat");
		
		ZButton atkbtn = new ZButton(eventAdder, "attack", "Attack", 0, 413, 122, 81);
		components.add(atkbtn);
		mouseObjects.add(atkbtn);
		
		ZButton spellbtn = new ZButton(eventAdder, "spell","Spell", 120, 413, 122, 81);
		components.add(spellbtn);
		mouseObjects.add(spellbtn);
		
		ZButton itembtn = new ZButton(eventAdder, "combat, playerItem, String", "Items", 0, 491, 122, 81);
		components.add(itembtn);
		mouseObjects.add(itembtn);
		
		ZButton retreatbtn = new ZButton(eventAdder, "sceneWorldMap", "Retreat", 120, 491, 122, 81);
		components.add(retreatbtn);
		mouseObjects.add(retreatbtn);
		
		ZButton enemyhp = new ZButton(eventAdder, "NONE", ""+enemy.getValueOfVital("Health"), 240, 0,292, 93);
		components.add(enemyhp);
		ZButton playerhp = new ZButton(eventAdder, "NONE",""+player.getValueOfVital("Health"),240, 492, 292, 80);
		components.add(playerhp);
		ZButton playermana = new ZButton(eventAdder, "NONE",""+player.getValueOfVital("Energy"),240, 471, 292, 23);
		components.add(playermana);
		ZButton enemymana = new ZButton(eventAdder, "NONE",""+enemy.getValueOfVital("Energy"),240, 100, 292, 33);
		components.add(enemymana);
	}
}