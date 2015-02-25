package gui;

import java.util.ArrayList;
import java.util.List;

import character.Spell;
import core.EventAdder;
import core.GlobalStateManager;
import player.ItemSlot;
import player.Playable;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZTextArea;
import combat.Enemy;
import database.ImageDatabase;

/**
 * Combat is a subclass of JFrame for re-rendering the current JFrame
 * to this GUI if combat occurs.
 *
 * @author Andreas BÃ¤ckevik	& Daniel Edisnger
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
	private EventAdder eventAdder;
	private List<ZButton> spellButtons;
	private List<ZButton> itemButtons;
	private ZTextArea combatlog = new ZTextArea(560,294,240,300,10);
	
	/*
	 * The constructor of Combat creates all the necessary components and adding relevant player spells.
	 * It also sets actionListeners to work with the game-logic.
	 */
	public CombatViewer (List<ZEntity> entities, EventAdder eventAdder, Playable players,  Enemy enemy) {
		super(ImageDatabase.getInstance().getImage("bgCombatForest.jpg"), 0, 0, eventAdder, entities);
		this.player = players;
		this.eventAdder = eventAdder;
		spellButtons = new ArrayList<>();
		itemButtons = new ArrayList<>();
		
		ZImage enemyIcon = new ZImage(enemy.getImage(), 300, 250);
		components.add(enemyIcon);
		
		GlobalStateManager.getInstance().updateCurrentState("InCombat");
		
		ZButton atkbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatAttack.jpg"), 0, 440, eventAdder, "attack");
		components.add(atkbtn);
		entities.add(atkbtn);
		
		ZButton spellbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatSpell.jpg"), 0, 520, eventAdder, "spellMenu");
		components.add(spellbtn);
		entities.add(spellbtn);
		
		ZButton itembtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatItem.jpg"), 120, 440, eventAdder, "itemMenu");
		components.add(itembtn);
		entities.add(itembtn);
		
		ZButton retreatbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatRetreat.jpg"), 120, 520, eventAdder, "retreat");
		components.add(retreatbtn);
		entities.add(retreatbtn);
		
		ZButton nextturnbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatNextTurn.jpg"), 677, 345, eventAdder, "nextTurn");
		components.add(nextturnbtn);
		entities.add(nextturnbtn);
		
		components.add(combatlog);
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
	public void spellMenu(){
		int yCord=0, j=0;
		for(Spell spell : player.getSpellBook().getSpells()){
			spellButtons.add(new ZButton(spell.getName(),240,440+yCord,100,30,eventAdder,"spell,"+spell.getName()));
			components.add(spellButtons.get(j));
			entities.add(spellButtons.get(j));
			j++;
			yCord=+30;
		}
	}
	
	public void clickedSpell(){
		for(ZButton btn : spellButtons){
			components.remove(btn);
		}
	}
	
	public void itemMenu(){
		int yCord=0, j=0;
		boolean addedHealthPotion = false;
		boolean addedEnergyPotion = false;
		for(ItemSlot itemslot : player.getInventory().getItems()){
			if(itemslot.getItem().getName().equals("Minor Healing Potion") && addedHealthPotion==false){
				itemButtons.add(new ZButton(itemslot.getItem().getName(),240,440+yCord,200,30,eventAdder,"item,"+itemslot.getItem().getName()));
				components.add(itemButtons.get(j));
				entities.add(itemButtons.get(j));
				addedHealthPotion=true;
				j++;
				yCord=+30;
			}else if(itemslot.getItem().getName().equals("Minor Energy Potion") && addedEnergyPotion==false){
				itemButtons.add(new ZButton(itemslot.getItem().getName(),240,440+yCord,200,30,eventAdder,"item,"+itemslot.getItem().getName()));
				components.add(itemButtons.get(j));
				entities.add(itemButtons.get(j));
				addedEnergyPotion=true;
				j++;
				yCord=+30;
			}
		}
	}
	
	public void clickedItem(){
		for(ZButton btn : itemButtons){
			components.remove(btn);
		}
	}
	
	public void addText(String s){
		combatlog.addText(s);
	}
}
