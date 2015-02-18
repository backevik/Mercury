package core;

import character.Spell;
import character.SpellBook;
import constants.Attributes;
import constants.Skills;
import constants.Vitals;
import player.Playable;
import player.Player;
import player.Quest;
import combat.Enemy;
import combat.Combat;

public class Test {
	
	private Player player;
	private Enemy enemy;
	
	public Test () {
		Spell fireball = new Spell("fireball","ball of fire",20,"damage");
		Spell Heal = new Spell("heal","Heal yourself",20,"heal");

		player = new Player("TestChar");
		enemy = new Enemy("fittsture");
		player.getPC().getSpellBook().addSpell(fireball);
		player.getPC().getSpellBook().addSpell(Heal);
		Combat combat = new Combat(player.getPC(),enemy);
		CombatGUI cGUI = new CombatGUI(player.getPC(),enemy,combat);
		printPlayerData ();
	}

	private void printPlayerData () {
		printGeneric ();
		printAttributes ();
		printVitals ();
		printSkills ();
		printSpellBook ();
		printInventory ();
		printQuestLog ();
	}
	private void printGeneric () {
		System.out.println(player.getPC().getName());
		System.out.println("Level:  \t" + player.getPC().getLevel());
		System.out.println("Experience:\t" + player.getPC().getExpCur() + " / " + player.getPC().getExpTnl());
	}
	
	private void printAttributes () {
		System.out.println("\n--- Attributes");
		for (String s : Attributes.getNames()) {
			System.out.println(s + ":\t" + player.getPC().getValueOfAttribute(s));
		}
	}
	
	private void printVitals () {
		System.out.println("\n--- Vitals");
		for (String s : Vitals.getNames()) {
			System.out.println(s + ":\t" + player.getPC().getValueOfVital(s) + " / " + player.getPC().getMaxOfVital(s));
		}
	}
	
	private void printSkills () {
		System.out.println("\n--- Skills");
		for (String s : Skills.getNames()) {
			System.out.println(s + ":    \t" + player.getPC().getValueOfSkill(s));
		}
	}
	
	private void printSpellBook () {
		System.out.println("\n--- Spell Book");
		for (Spell s : player.getPC().getSpellBook().getSpells()) {
			System.out.println(s.getName());
			System.out.println(s.getDescription());
			System.out.println("Energy Cost: " + s.getEnergyCost());
		}
	}
	
	private void printInventory () {
		System.out.println("\n--- Inventory");
		for (Spell s : player.getPC().getSpellBook().getSpells()) {
			System.out.println(s.getName());
			System.out.println(s.getDescription());
			System.out.println("Energy Cost: " + s.getEnergyCost());
		}
	}
	
	private void printQuestLog () {
		System.out.println("\n--- Quest Log");
		for (Quest s : player.getQuestLog ().getQuests()) {
			System.out.println(s.getName());
			System.out.println(s.getDescription());
			System.out.println("Status: " + s.getQuestStatus().toString());
		}
	}
	public static void main(String[] args) {
		new Test();
	}	

}