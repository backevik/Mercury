package player;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.4
 * @since       2015-02-01
 */

public class Quest {
	
	private QuestStatus questStatus;
	private String name;
	private String description;
	
	public Quest (String name, String description) {
		questStatus = QuestStatus.UNDISCOVERED;
		this.name = name;
		this.description = description;
	}
	
	public QuestStatus getQuestStatus () {
		return questStatus;
	}
	
	public void setQuestStatus (QuestStatus questStatus) {
		this.questStatus = questStatus;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDescription () {
		return description;
	}
}
