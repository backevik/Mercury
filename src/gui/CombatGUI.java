package gui;

import java.awt.Image;
import java.util.List;

import gui.ZButton;
import gui.ZContainer;
import core.Drawable;
import core.EventAdder;

import player.Playable;
import combat.Combat;
import combat.Enemy;
import core.MouseObject;
import database.GameDataManager;
	/*
	* Combat is a subclass of JFrame for re-rendering the current JFrame
	* to this GUI if combat occurs.
	*
	* @author Andreas Bäckevik
	* @version 0.1
	* @since 2015-01-30
	*/
	public class CombatGUI extends ZContainer implements Drawable{
		/*
		private JPanel contentPane;
		private JTextArea textarea;
		private JPopupMenu menu;
		private JProgressBar playerHealth;
		private JProgressBar manaBar;
		private JProgressBar enemyHealth;
		private JProgressBar enemymanaBar;
		private JMenuItem spell_menu;
		private JMenuItem item_menu;
		*/
		//private JButton atkBtn;
		//private JButton spellBtn;
		//private JButton itemBtn;
		//private JButton retreatBtn;
		private ZButton atkbtn;
		private ZButton spellbtn;
		private ZButton itembtn;
		private ZButton retreatbtn;
		
		private Playable player;
		private Enemy enemy;
		private Combat combat;
		
	/*
	 * The constructor of Combat creates all the necessary components and adding relevant player spells.
	 * It also sets actionListeners to work with the game-logic.
	 */
		public CombatGUI(List<MouseObject> mouseObjects, EventAdder eventAdder, Playable players, Enemy enemies, Combat combat) {
			super(eventAdder,GameDataManager.getInstance().getImage("bgDefault.jpg"), 0, 0, mouseObjects);
			this.player = players;
			this.enemy = enemies;
			this.combat = combat;
			/*
			menu = new JPopupMenu("Menu");
			
			textarea = new JTextArea();
			textarea.setEditable(false);
			textarea.setDropMode(DropMode.INSERT);
			textarea.setBackground(SystemColor.controlHighlight);
			textarea.setForeground(new Color(0, 0, 0));
			textarea.setBounds(531, 413, 263, 159);
			textarea.setFont(new Font("Serif",Font.PLAIN,20));
			getContentPane().add(textarea);
				
			JButton atkBtn = new JButton("Attack");
			atkBtn.setBounds(0, 413, 122, 81);
			getContentPane().add(atkBtn);
			*/
			//super(eventAdder, new image(image,0,0), 0, 0, mouseObjects);
			
			ZButton atkbtn = new ZButton(eventAdder, "combat, playerAttack", "Attack", 0, 413, 122, 81);
			components.add(atkbtn);
			mouseObjects.add(atkbtn);
			
			ZButton spellbtn = new ZButton(eventAdder, "combat, playerSpell, String", "Spell", 120, 413, 122, 81);
			components.add(spellbtn);
			mouseObjects.add(spellbtn);
			
			ZButton itembtn = new ZButton(eventAdder, "combat, playerItem, String", "Items", 0, 491, 122, 81);
			components.add(itembtn);
			mouseObjects.add(itembtn);
			
			ZButton retreatbtn = new ZButton(eventAdder, "SceneWorldMap", "Retreat", 120, 491, 122, 81);
			components.add(retreatbtn);
			mouseObjects.add(retreatbtn);
			
			/*
			atkBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
						combat.playerAttack();
						playerHealth.setValue((int)player.getValueOfVital("Health"));
						enemyHealth.setValue((int)enemy.getValueOfVital("Health"));
						enemyHealth.setString(""+(int)enemy.getValueOfVital("Health"));
						playerHealth.setString(""+(int)player.getValueOfVital("Health"));
				}
			});
			
			JButton spellBtn = new JButton("Spells");
			spellBtn.setBounds(120, 413, 122, 81);
			getContentPane().add(spellBtn);
			
			spellBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					menu.show(spellBtn, spellBtn.getWidth(), 0);				
				}
			});
			
			for(Spell spell : player.getSpellBook().getSpells()){
				spell_menu = new JMenuItem(spell.getName());
				spell_menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						combat.playerSpell(spell.getName());
						playerHealth.setValue((int)player.getValueOfVital("Health"));
						enemyHealth.setValue((int)enemy.getValueOfVital("Health"));
						enemyHealth.setString(""+(int)enemy.getValueOfVital("Health"));
						playerHealth.setString(""+(int)player.getValueOfVital("Health"));
						manaBar.setValue((int)player.getValueOfVital("Energy"));
						manaBar.setString(""+(int)player.getValueOfVital("Energy"));
					}
				});
				menu.add(spell_menu);
			}
			
			JButton itemBtn = new JButton("Items");
			itemBtn.setBounds(0, 491, 122, 81);
			getContentPane().add(itemBtn);
			
			itemBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					combat.playerItem(1);
				}
			});
			
			for(ItemSlot IS : player.getInventory().getItems()){
				item_menu = new JMenuItem(IS.getItem().getName());
				item_menu.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						combat.playerItem(IS.getItem().getName());
					}
				});
				menu.add(item_menu);
			}
					
			JButton retreatBtn = new JButton("Retreat: "+ combat.retreatChance(player));
			retreatBtn.setBounds(120, 491, 122, 81);
			getContentPane().add(retreatBtn);
			
			retreatBtn.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  combat.playerRetreat();
					  } 
					});		
			
			playerHealth = new JProgressBar();
			playerHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
			playerHealth.setStringPainted(true);
			playerHealth.setToolTipText("Player health");
			playerHealth.setBounds(240, 492, 292, 80);
			playerHealth.setForeground(new Color(0, 204, 0));
			playerHealth.setMaximum((int)player.getValueOfVital("Health"));
			playerHealth.setValue((int)player.getValueOfVital("Health"));
			playerHealth.setString(""+(int)player.getValueOfVital("Health"));
			getContentPane().add(playerHealth);
			
			manaBar = new JProgressBar();
			manaBar.setFont(new Font("Tahoma", Font.PLAIN, 18));
			manaBar.setToolTipText("Player mana");
			manaBar.setStringPainted(true);
			manaBar.setForeground(new Color(51, 102, 255));
			manaBar.setBounds(240, 471, 292, 23);
			manaBar.setMaximum((int)player.getValueOfVital("Energy"));
			manaBar.setValue((int)player.getValueOfVital("Energy"));
			manaBar.setString(""+(int)player.getValueOfVital("Energy"));
			getContentPane().add(manaBar);
			
			enemyHealth = new JProgressBar();
			enemyHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
			enemyHealth.setToolTipText("Enemy health");
			enemyHealth.setForeground(new Color(0, 204, 0));
			enemyHealth.setStringPainted(true);
			enemyHealth.setBounds(240, 0, 292, 33);
			enemyHealth.setMaximum((int)enemy.getValueOfVital("Health"));
			enemyHealth.setValue((int)enemy.getValueOfVital("Health"));
			enemyHealth.setString(""+(int)enemy.getValueOfVital("Health"));
			getContentPane().add(enemyHealth);
			
			enemymanaBar = new JProgressBar();
			enemymanaBar.setFont(new Font("Tahoma", Font.PLAIN, 18));
			enemymanaBar.setToolTipText("Enemy mana");
			enemymanaBar.setForeground(new Color(0, 204, 0));
			enemymanaBar.setStringPainted(true);
			enemymanaBar.setBounds(240, 33, 292, 33);
			enemymanaBar.setMaximum((int)enemy.getValueOfVital("Energy"));
			enemymanaBar.setValue((int)enemy.getValueOfVital("Energy"));
			enemymanaBar.setString(""+(int)enemy.getValueOfVital("Energy"));
			getContentPane().add(enemymanaBar);
			*/
			
		}


		public void remove() {

		}
	}
