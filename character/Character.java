package character;

import java.util.HashMap;

import java.util.Map;

import constants.Attributes;
import constants.Skills;
import constants.Vitals;
import player.Inventory;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.2
 * @since       2015-02-01
 */

public abstract class Character
{
	private String name;
	
	//private final CharacterStatus characterStatus = new CharacterStatus ();
	
	private final Map<String, Attribute> attributes = new HashMap<>();
	private final Map<String, Skill> skills = new HashMap<>();
	private final Map<String, Vital> vitals = new HashMap<>();
	
	private final SpellBook spellBook = new SpellBook ();
	private final Inventory inventory = new Inventory ();
	
	public Character (String name) {
		// give the character a name
		this.name = name;
		
		// initiate all attributes based on constants.Attributes
		for (String s : Attributes.getNames()) {
			attributes.put(s, new Attribute (5.0d));
		}
		
		// initiate all attributes based on constants.Skills
		for (String s : Skills.getNames()) {
			skills.put(s, new Skill(0.0d));
		}
		
		// initiate all attributes based on constants.Vitals
		for (String s : Vitals.getNames()) {
			vitals.put(s, new Vital(50.0d));
		}
		
		updateSkills ();
		updateVitals ();
	}
	
	/**
	 * @return 	name of this character
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Get the current value of attribute by name s
	 * @param s - the name of the attribute
	 * @return value of Attribute by name s
	 */
	public double getValueOfAttribute (String s) {
		return attributes.get(s).getValue();
	}
	
	/**
	 * Get the current value of skill by name s
	 * @param s - the name of the skill
	 * @return value of Skill by name s
	 */
	public double getValueOfSkill (String s) {
		return skills.get(s).getValue();
	}
	
	/**
	 * Get the current value of attribute by name s
	 * @param s - the name of the attribute
	 * @return value of Attribute by name s
	 */
	public double getValueOfVital (String s) {
		return vitals.get(s).getValue();
	}
	/**
	 * Get the maximum value of vital by name s
	 * @param s - the name of the vital
	 * @return value of Vital by name s
	 */
	public double getMaxOfVital (String s) {
		return vitals.get(s).getMax();
	}
	/**
	 * Reduce the value of vital by name s with number value
	 * @param s - the name of the vital
	 * @param value - the amount reduced on vital
	 */
	public void reduceVital(String s, double value){
		vitals.get(s).reduceVital(value);
	}
	/**
	 * heal the value of vital by name s with number value
	 * @param s - the name of the vital
	 * @param value - the amount healed on vital
	 */
	public double healVital(String s, double value){
		if((vitals.get(s).getValue()+value)>vitals.get(s).getMax()){
			double diff = (vitals.get(s).getValue()+value)-vitals.get(s).getMax();
			vitals.get(s).healVital(diff);
			return diff;
		}else{
			vitals.get(s).healVital(value);
			return value;
		}
	}
	/**
	 * Returns a reference to the Character's inventory
	 * @return Inventory from this character
	 */
	public Inventory getInventory(){
		return inventory;
	}
	/**
	 * Returns a reference to the Character's spell book
	 * @return Spellbook from this character
	 */
	public SpellBook getSpellBook () {
		return spellBook;
	}
	
	protected Map<String, Attribute> getAttributes () {
		return attributes;
	}
	
	protected Map<String, Skill> getSkills () {
		return skills;
	}
	
	protected Map<String, Vital> getVitals () {
		return vitals;
	}
	
	protected void updateSkills () {
		for (Skill s : skills.values()) {
			s.updateValue();
		}
	}
	
	protected void updateVitals () {
		for (Vital v : vitals.values()) {
			v.updateValue();
		}
	}
}