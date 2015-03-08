package gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import character.Spell;
import core.EventAdder;
import character.ItemSlot;
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
 * Sets up and holds all gui elements to the combat frame
 * @author Andreas Backevik
 * @version 1.0
 * @since 2015-02-21
 */

public class CombatViewer extends ZContainer implements ZDrawable
{
	private ZButton atkbtn;
	private ZButton spellbtn;
	private ZButton itembtn;
	private ZButton retreatbtn;
	private ZButton nextturnbtn;
	
	private Playable player;
	private Enemy enemy;
	private EventAdder eventAdder;
	private List<ZButton> spellButtons;
	private List<ZButton> itemButtons;
	private List<Image> hpBar;
	private List<Image> manaBar;
	private ZTextArea combatlog;
	private ZAnimation playerHP;
	private ZAnimation playerMana;
	private ZAnimation enemyHP;
	private ZAnimation enemyMana;

	/**
	 * The constructor initializes all necessary information for the combat GUI to work uniquely for each fight. And adds all GUI elements.
	 * @param entities copy of entity list, to add components
	 * @param eventAdder reference to the event queue
	 * @param players list with playable characters.
	 * @param e the specific encounter for this combat that includes enemies
	 */
	public CombatViewer (List<ZEntity> entities, EventAdder eventAdder, Playable players, Encounter e) {
		super(ImageDatabase.getInstance().getImage("bgCombatForest.jpg"), 0, 0, eventAdder, entities);
		this.player = players;
		this.enemy = e.getEnemies().get(0);
		this.eventAdder = eventAdder;
		spellButtons = new ArrayList<>();
		itemButtons = new ArrayList<>();
		hpBar = new ArrayList<>();
		manaBar = new ArrayList<>();
		
		ZImage enemyIcon = new ZImage(enemy.getImage(), 300, 250);
		components.add(enemyIcon);
		
		atkbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatAttack.jpg"), 0, 440, eventAdder, "attack");
		components.add(atkbtn);
		entities.add(atkbtn);
		
		spellbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatSpell.jpg"), 0, 520, eventAdder, "spellMenu");
		components.add(spellbtn);
		entities.add(spellbtn);
		
		itembtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatItem.jpg"), 120, 440, eventAdder, "itemMenu");
		components.add(itembtn);
		entities.add(itembtn);
		
		retreatbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatRetreat.jpg"), 120, 520, eventAdder, "retreat");
		components.add(retreatbtn);
		entities.add(retreatbtn);
		
		nextturnbtn = new ZButton(ImageDatabase.getInstance().getImage("btnCombatNextTurn.jpg"), 675, 360, eventAdder, "nextTurn");
		components.add(nextturnbtn);
		entities.add(nextturnbtn);
		
		combatlog = new ZTextArea(560,445,280,155,14);
		components.add(combatlog);
		
		for(int i=37;i>0;i--){
			hpBar.add(ImageDatabase.getInstance().getImage("guiCombatHealthbar"+i+".png"));
			manaBar.add(ImageDatabase.getInstance().getImage("guiCombatManabar"+i+".png"));
		}
		
		playerMana = new ZAnimation(manaBar,240,482,0);
		components.add(playerMana);
		
		playerHP = new ZAnimation(hpBar,240,522,0);
		components.add(playerHP);
		
		enemyHP = new ZAnimation(hpBar,240,40,0);
		components.add(enemyHP);
		
		enemyMana = new ZAnimation(manaBar,240,0,0);
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
	/**
	 * Opens spell menu with dynamically loaded spells from players spellbook to show in the GUI
	 */
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
	/**
	 * Removes the spell menu when spell is used.
	 */
	public void clickedSpell(){
		for(ZButton btn : spellButtons){
			components.remove(btn);
			entities.remove(btn);
		}
	}
	/**
	 * Opens item menu with dynamically loaded items from players inventory to show in the GUI
	 */
	public void itemMenu(){
		int yCord=0, j=0;
		boolean addedHealthPotion = false;
		boolean addedEnergyPotion = false;
		for(ItemSlot itemslot : player.getInventory().getItems()){
			if(itemslot.getItem().getName().contains("Healing") && addedHealthPotion==false){
				itemButtons.add(new ZButton(itemslot.getItem().getName(),240,440+yCord,200,30,eventAdder,"item,"+itemslot.getItem().getName()));
				components.add(itemButtons.get(j));
				entities.add(itemButtons.get(j));
				addedHealthPotion=true;
				j++;
				yCord=+30;
			}
			if(itemslot.getItem().getName().contains("Energy") && addedEnergyPotion==false){
				itemButtons.add(new ZButton(itemslot.getItem().getName(),240,440+yCord,200,30,eventAdder,"item,"+itemslot.getItem().getName()));
				components.add(itemButtons.get(j));
				entities.add(itemButtons.get(j));
				addedEnergyPotion=true;
				j++;
				yCord=+30;
			}
		}
	}
	/**
	 * Removes the item menu when item is used.
	 */
	public void clickedItem(){
		for(ZButton btn : itemButtons){
			components.remove(btn);
			entities.remove(btn);
		}
	}
	/**
	 * Prints text from combat logic in the log with s
	 * @param s - text to print in log
	 */
	public void addText(String s){
		combatlog.addText(s);
	}
	/**
	 * Updates all the visuals with updated health/energy every time it changes.
	 */
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
		if(playerHpMax>37 || playerHpMax<=0){
			playerHpMax=37;
		}
		if(playerManaMax<=0){
			playerManaMax=2;
		}
		if(playerManaMax>31){
			playerManaMax=37;
		}
		if(enemyHpMax>37 || enemyHpMax<=0){
			enemyHpMax=37;
		}
		if(enemyManaMax>31){
			enemyManaMax=37;
		}
		if(enemyManaMax<=0){
			enemyManaMax=2;
		}
		playerHP = new ZAnimation(hpBar,240,522,37-playerHpMax);
		enemyHP = new ZAnimation(hpBar,240,40,37-enemyHpMax);
		playerMana = new ZAnimation(manaBar,240,482,37-playerManaMax);
		enemyMana = new ZAnimation(manaBar,240,0,37-enemyManaMax);
		components.add(playerHP);
		components.add(enemyHP);
		components.add(playerMana);
		components.add(enemyMana);
	}
}
