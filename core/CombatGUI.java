package core;

	import java.awt.EventQueue;

	import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

	import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	import javax.swing.DropMode;

	import java.awt.Color;

	import javax.swing.JProgressBar;

	import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

	import javax.swing.JTextArea;

import player.Playable;
import combat.Combat;
import combat.Enemy;
	/*
	* Combat is a subclass of JFrame for re-rendering the current JFrame
	* to this GUI if combat occurs.
	*
	* @author Andreas Bäckevik
	* @version 0.1
	* @since 2015-01-30
	*/
	public class CombatGUI extends JFrame{
		
		private ArrayList<String> spellBook; //TEMP VAR
		
		private JPanel contentPane;
		private JTextArea textarea;
		private JPopupMenu menu;
		private JProgressBar playerHealth;
		private JProgressBar manaBar;
		private JProgressBar enemyHealth;
		private JMenuItem spell_menu;
		
		private JButton atkBtn;
		private JButton spellBtn;
		private JButton itemBtn;
		private JButton retreatBtn;
		
		Playable player;
		Enemy enemy;
		Combat combat;
		
	/*
	 * The constructor of Combat creates all the necessary components and adding relevant player spells.
	 * It also sets actionListeners to work with the game-logic.
	 */
		public CombatGUI(Playable players, Enemy enemies,Combat combat) {
			this.player = players;
			this.enemy = enemies;
			this.combat = combat;
			
			setContentPane(new JLabel(new ImageIcon("nice_country_road-903315.jpg")));

			setTitle("Game");
			
			spellBook = new ArrayList<>(); //TEMPVAR
			spellBook.add("fireball"); //TEMPVAR
			spellBook.add("ice nova"); //TEMPVAR

			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 600);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);	
			
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
			
			for(String spell : spellBook){
				spell_menu = new JMenuItem(spell);
				spell_menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						combat.playerSpell(spell);
						playerHealth.setValue((int)player.getValueOfVital("Health"));
						enemyHealth.setValue((int)enemy.getValueOfVital("Health"));
						enemyHealth.setString(""+(int)enemy.getValueOfVital("Health"));
						playerHealth.setString(""+(int)player.getValueOfVital("Health"));
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
			
			setVisible(true);
		}
	}
