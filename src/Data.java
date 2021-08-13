/* CS2210a
 * Brendan Bain
 * 251086487
*/
public class Data {
	
	String keyAttribute;
	int Score, Level;

	public Data(String key, int score, int level) {
		keyAttribute = key;
		Score = score;
		Level = level;
	}
	
	public String getKey() {
		return keyAttribute;
	}
	
	public int getScore() {
		return Score;
	}
	
	public int getLevel() {
		return Level;
	}
	
}
