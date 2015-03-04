package character;

import java.util.HashMap;

import java.util.Map;

import constants.Attributes;
import constants.Skills;
import constants.Vitals;
import player.Inventory;

/**
 * Where a character essentially is created with all basic statistics and info.
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     1.0
 * @since       2015-02-01
 */

public abstract class Character
{
	private String name;
	private int level;
	
	private final Map<String, Attribute> attributes = new HashMap<>();
	private final Map<String, Skill> skills = new HashMap<>();
	private final Map<String, Vital> vitals = new HashMap<>();
	
	private final SpellBook spellBook = new SpellBook ();
	private final Inventory inventory = new Inventory ();
	/**
	 * Constructor
	 * @param name - name of the character that is created.
	 */
	public Character (String name) {
		// give the character a name
		this.name = name;
		level = 1;
		
		// initiate all attributes based on constants.Attributes
		for (String s : Attributes.getNames()) {
			attributes.put(s, new Attribute (5.0d));
		}
		
		// initiate all attributes based on constants.Skills
		for (String s : Skills.getNames()) {
			skills.put(s, new Skill(5.0d));
		}
		
		// initiate all attributes based on constants.Vitals
		for (String s : Vitals.getNames()) {
			vitals.put(s, new Vital(50.0d));
		}
		
		updateAttributes (level);
		updateSkills (level);
		updateVitals (level);
	}
	
	/**
	 * Returns name of character
	 * @return 	name of this character
	 */
	public String getName () {
		return name;
	}
	/**
	 * Return level of character
	 * @return level of this character
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Set level of the character and updates skills/vitals with new level.
	 * @param level of character
	 */
	public void setLevel(int level){
		this.level = level;
		updateSkills (level);
		updateVitals (level);
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
		if(s.equals("Energy")){
			vitals.get(s).reduceVital(value);
		}
		if(s.equals("Health")){
			vitals.get(s).reduceVital(value);
		}		
	}
	/**
	 * heal the value of vital by name s with number value
	 * @param s - the name of the vital
	 * @param value - the amount healed on vital
	 * @return heal - the difference between max and current health
	 * @return value - the amount healed
	 */
	public double healVital(String s, double value){
		if(vitals.get(s).getMax()<(vitals.get(s).getValue()+value)){
			value = vitals.get(s).getMax() - vitals.get(s).getValue();
		}
		vitals.get(s).healVital(value);
		return value;
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
	/**
	 * Returns a map with String key and Attribute values
	 * @return map with String key and Attribute values
	 */
	protected Map<String, Attribute> getAttributes () {
		return attributes;
	}
	/**
	 * Returns a map with String key and Skills values
	 * @return map with String key and Attribute values
	 */
	protected Map<String, Skill> getSkills () {
		return skills;
	}
	/**
	 * Returns a map with String key and Vital values
	 * @return map with String key and Vital values
	 */
	protected Map<String, Vital> getVitals () {
		return vitals;
	}
	/**
	 * Updates skill values accordingly with level parameter
	 * @param level of character
	 */
	protected void updateSkills (int level) {
		for (Skill s : skills.values()) {
			s.updateValue(level);
		}
	}
	/**
	 * Updates vital values accordingly with level parameter
	 * @param level of character				
	 */
	protected void updateVitals (int level) {
		for (Vital v : vitals.values()) {
			v.setMax(level);
			v.updateValue(level);
		}
	}
	/**
	 * Updates attribute values accordingly with level parameter
	 * @param level of character
	 */
	protected void updateAttributes (int level) {
		for (Attribute a : attributes.values()){
			a.updateValue(level);
		}
	}
}