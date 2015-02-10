package proj_gui;

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

import javax.swing.JTextArea;

/*
* Combat is a subclass of JFrame for re-rendering the current JFrame
* to this GUI if combat occurs.
*
* @author Andreas Bäckevik
* @version 0.1, 2015-01-30
* @since 8.0
*/
public class Combat extends JFrame {
	
	private ArrayList<String> spellBook; //TEMP VAR
	
	private JPanel contentPane;
	private JTextArea textarea;
	private JPopupMenu menu;
	private JProgressBar playerHealth;
	private JProgressBar manaBar;
	private JProgressBar enemyHealth;
	private JMenuItem spell_menu;
	
	//TEMPORARY VARIABLE CONSTANTS
	private int pHp=300,pMana=200,eHp=200;

	public static void main(String[] args) { //Dummy main for testing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new Combat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/*
 * The constructor of Combat creates all the necessary components and adding relevant player spells.
 * It also sets actionListeners to work with the game-logic.
 */
	public Combat() {
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
				textarea.setText("Attacked: " + "DAMAGE_HERE" + "\n" + textarea.getText()); //Needs change to variable instead of DAMAGE_HERE
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
		
		JButton btnNewButton = new JButton("Items");
		btnNewButton.setBounds(0, 491, 122, 81);
		atkBtn.getInputMap().put(KeyStroke.getKeyStroke(keybindSpells),
				"INSERT COMMAND HERE");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CODE FOR VIEWING ITEMS IS NEEDED HERE
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Retreat");
		btnNewButton_2.setBounds(120, 491, 122, 81);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//CODE FOR RETURNING TO WORLDMAP IS NEEDED HERE
			}
		});
		getContentPane().add(btnNewButton_2);
		
		playerHealth = new JProgressBar();
		playerHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerHealth.setStringPainted(true);
		playerHealth.setToolTipText("Player health");
		playerHealth.setBounds(240, 492, 292, 80);
		playerHealth.setForeground(new Color(0, 204, 0));
		playerHealth.setMaximum(300);
		playerHealth.setValue(pHp);
		playerHealth.setString(""+pHp);
		getContentPane().add(playerHealth);
		
		manaBar = new JProgressBar();
		manaBar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		manaBar.setToolTipText("Player mana");
		manaBar.setStringPainted(true);
		manaBar.setForeground(new Color(51, 102, 255));
		manaBar.setBounds(240, 471, 292, 23);
		manaBar.setMaximum(300);
		manaBar.setValue(pMana);
		manaBar.setString(""+pMana);
		getContentPane().add(manaBar);
		
		for(String spell : spellBook){
			spell_menu = new JMenuItem(spell);
			spell_menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					textarea.setText(spell +": DAMAGE_HERE \n" + textarea.getText()); //Needs change to variable instead of DAMAGE_HERE
				}
			});
			menu.add(spell_menu);
		}
		
		enemyHealth = new JProgressBar();
		enemyHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		enemyHealth.setToolTipText("Enemy health");
		enemyHealth.setForeground(new Color(0, 204, 0));
		enemyHealth.setStringPainted(true);
		enemyHealth.setBounds(240, 0, 292, 33);
		enemyHealth.setMaximum(200);
		enemyHealth.setValue(eHp);
		getContentPane().add(enemyHealth);

	}
}