import java.util.Arrays;

public class wheeee {

	public static void main(String[] args) {
		String[] topPlayers = {"Bob", "Joe", "Jeff", "Bert", "Rob"};
		int[] topScores = {10, 7, 5, 3, 2};
		
		System.out.println(updateTopScore(topScores, topPlayers, 1, "Lynn"));
		System.out.println(Arrays.toString(topPlayers));
		System.out.println(Arrays.toString(topScores));

	}

	public static int updateTopScore(int[] topScores, String[] topPlayers, int score, String player) {
		int pos = -1;
		for (int i = 3; i >= 0; i--) {
			if (score < topScores[i]) {
				break;
			}
			pos = i;
		}
		if (pos == -1) {
			return -1;
		}
		
		for (int i = 4; i > pos; i--) {
			topPlayers[i] = topPlayers [i-1];
			topScores[i] = topScores[i-1];
		}
		topPlayers[pos] = player;
		topScores[pos] = score;
		
		return (pos+1);
	}
}
