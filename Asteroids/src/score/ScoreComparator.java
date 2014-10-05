package score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

	@Override
	public int compare(Score score1, Score score2) {
		if (score1.getScore() < score2.getScore()){
			return 1;
		}
		else{
			return -1;
		}
	}

}
