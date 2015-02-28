package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

import character.Spell;
import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;
import player.ItemSlot;
import player.Playable;
import zlibrary.ZAnimation;
import zlibrary.ZButton;
import zlibrary.ZContainer;
import zlibrary.ZDrawable;
import zlibrary.ZEntity;
import zlibrary.ZImage;
import zlibrary.ZTextArea;
import combat.Encounter;
import combat.Enemy;
import database.ImageDatabase;

/**
 * Combat is a subclass of JFrame for re-rendering the current JFrame
 * to this GUI if combat occurs.
 *
 * @author Andreas BÃƒÆ’Ã‚Â¤ckevik	& Daniel Edisnger
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
	private EventAdder eventAdder;
	private List<ZButton> spellButtons;
	private List<ZButton> itemButtons;
	private List<Image> hpBar;
	private List<Image> manaBar;
	private ZTextArea combatlog = new ZTextArea(560,294,240,300,10);
	private ZAnimation playerHP;
	private ZAnimation playerMana;
	private ZAnimation enemyHP;
	private ZAnimation enemyMana;
	
	/*
	 * The constructor of Combat creates all the necessary components and adding relevant player spells.
	 * It also sets actionListeners to work with the game-logic.
	 */
	public CombatViewer (List<ZEntity> entities, EventAdder eventAdder, Playable players, Enemy enemy) {
		super(ImageDatabase.getInstance().getImage("bgCombatForest.jpg"), 0, 0, eventAdder, entities);
		this.player = players;
		this.enemy = enemy;
		this.eventAdder = eventAdder;
		spellButtons = new ArrayList<>();
		itemButtons = new ArrayList<>();
		hpBar = new ArrayList<>();
		manaBar = new ArrayList<>();
		
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
		
		for(int i=37;i>0;i--){
			hpBar.add(ImageDatabase.getInstance().getImage("guiCombatHealthbar"+i+".png"));
			manaBar.add(ImageDatabase.getInstance().getImage("guiCombatManabar"+i+".png"));
		}
		
		playerMana = new ZAnimation(manaBar,240,482,0);
		components.add(playerMana);
		
		playerHP = new ZAnimation(hpBar,240,522,0);
		components.add(playerHP);
		
		enemyHP = new ZAnimation(hpBar,240,65,0);
		components.add(enemyHP);
		
		enemyMana = new ZAnimation(manaBar,240,25,0);
		components.add(enemyMana);
		
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
			entities.remove(btn);
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
			entities.remove(btn);
		}
	}
	
	public void addText(String s){
		combatlog.addText(s);
	}

	public void updateVisuals(){
		int playerHpMax,playerManaMax,enemyHpMax,enemyManaMax;
		playerHpMax=(int)Math.round((player.getValueOfVital("Health")/((player.getMaxOfVital("Health")+10)/37)))+3;
		System.out.println((int)Math.round((player.getValueOfVital("Health")/((player.getMaxOfVital("Health")+10)/37))));
		playerManaMax=(int)Math.round((player.getValueOfVital("Energy")/((player.getMaxOfVital("Energy")+5)/37)));
		enemyHpMax=(int)Math.round((enemy.getValueOfVital("Health")/((enemy.getMaxOfVital("Health")+10)/37)))+3;
		enemyManaMax=(int)Math.round((enemy.getValueOfVital("Energy")/((enemy.getMaxOfVital("Energy")+10)/37)));
		components.remove(playerHP);
		components.remove(playerMana);
		components.remove(enemyHP);
		components.remove(enemyMana);
		if(playerHpMax>37 || playerHpMax<0){
			playerHpMax=37;
		}
		if(playerManaMax>37 || playerManaMax<0){
			playerManaMax=2;
		}
		if(enemyHpMax>37 || enemyHpMax<0){
			enemyHpMax=37;
		}
		if(enemyManaMax>37 || enemyManaMax<0){
			enemyManaMax=2;
		}
		playerHP = new ZAnimation(hpBar,240,522,37-playerHpMax);
		enemyHP = new ZAnimation(hpBar,240,65,37-enemyHpMax);
		playerMana = new ZAnimation(manaBar,240,482,37-playerManaMax);
		enemyMana = new ZAnimation(manaBar,240,25,37-enemyManaMax);
		components.add(playerHP);
		components.add(enemyHP);
		components.add(playerMana);
		components.add(enemyMana);
	}
}
