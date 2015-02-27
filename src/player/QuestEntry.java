package player;

public class QuestEntry {

	private Quest quest;
	private boolean completed;
	
	public QuestEntry (Quest quest) {
		this.quest = quest;
	}
	
	public boolean getCompleted () {
		return completed;
	}
	
	public Quest getQuest () {
		return quest;
	}
	
	public void complete() {
		completed = true;
	}
}
