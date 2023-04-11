import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    static class Score {
        private final int attitude;
        private final int review;

        private final boolean isWonho;

        public Score(int attitude, int review, boolean isWonho) {
            this.attitude = attitude;
            this.review = review;
            this.isWonho = isWonho;
        }

        public int sumOfScore() {
            return this.attitude + this.review;
        }

        public boolean isWonho() {
            return isWonho;
        }
    }

    public int solution(int[][] scores) {
        List<Score> scoreList = new ArrayList<>();

        int[] wonho = scores[0];
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int maxReviewScore = 0;
        for (int[] score : scores) {
            if (maxReviewScore > score[1]) {
                if (Arrays.equals(score, wonho)) {
                    return -1;
                }
                continue;
            }
            maxReviewScore = score[1];
            scoreList.add(new Score(score[0], score[1],
                    Arrays.equals(score, wonho)));
        }
        scoreList.sort(Comparator.comparingInt(Score::sumOfScore).reversed());

        return (int) scoreList.stream()
                .takeWhile(score -> !score.isWonho())
                .count() + 1;
    }
}