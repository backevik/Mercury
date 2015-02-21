package gui;

import java.util.List;

import core.EventAdder;
import core.GlobalStateManager;
import player.Playable;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import combat.Enemy;
import database.GameDataManager;

/**
 * Combat is a subclass of JFrame for re-rendering the current JFrame
 * to this GUI if combat occurs.
 *
 * @author Andreas Bäckevik	& Daniel Edisnger
 * @version 0.3.1
 * @since 2015-02-21
 */

public class CombatViewer extends ZContainer implements ZDrawable
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
	public CombatViewer (List<ZEntity> entities, EventAdder eventAdder, Playable players,  Enemy enemies) {
		super(GameDataManager.getInstance().getImage("bgCombatForest.jpg"), 0, 0, eventAdder, entities);
		this.player = players;
		this.enemy = enemies;
		
		GlobalStateManager.getInstance().updateCurrentState("InCombat");
		
		ZButton atkbtn = new ZButton(GameDataManager.getInstance().getImage("btnCombatAttack.jpg"), 0, 440, eventAdder, "attack");
		components.add(atkbtn);
		entities.add(atkbtn);
		
		ZButton spellbtn = new ZButton(GameDataManager.getInstance().getImage("btnCombatSpell.jpg"), 0, 520, eventAdder, "spell");
		components.add(spellbtn);
		entities.add(spellbtn);
		
		ZButton itembtn = new ZButton(GameDataManager.getInstance().getImage("btnCombatItem.jpg"), 120, 440, eventAdder, "combat, playerItem, String");
		components.add(itembtn);
		entities.add(itembtn);
		
		ZButton retreatbtn = new ZButton(GameDataManager.getInstance().getImage("btnCombatRetreat.jpg"), 120, 520, eventAdder, "sceneWorldMap");
		components.add(retreatbtn);
		entities.add(retreatbtn);
		
		ZButton nextturnbtn = new ZButton(GameDataManager.getInstance().getImage("btnCombatNextTurn.jpg"), 677, 345, eventAdder, "nextTurn");
		components.add(nextturnbtn);
		entities.add(nextturnbtn);
		
		//GUI to add
		/*
		ZButton enemyhp = new ZButton(GameDataManager.getInstance().getImage("gui.jpg"), 0, 413, eventAdder, "NONE");
		components.add(enemyhp);
		ZButton playerhp = new ZButton(GameDataManager.getInstance().getImage("gui.jpg"), 0, 413, eventAdder, "NONE");
		components.add(playerhp);
		ZButton playermana = new ZButton(GameDataManager.getInstance().getImage("gui.jpg"), 0, 413, eventAdder, "NONE");
		components.add(playermana);
		ZButton enemymana = new ZButton(GameDataManager.getInstance().getImage("gui.jpg"), 0, 413, eventAdder, "NONE");
		components.add(enemymana);
		*/
	}
}