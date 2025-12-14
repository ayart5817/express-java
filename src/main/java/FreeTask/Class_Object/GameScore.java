package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameScore implements Comparable<GameScore> {

    String playerName;
    Integer score;

    @Override
    public String toString() {
        return "GameScore{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameScore gameScore = (GameScore) o;
        return Objects.equals(playerName, gameScore.playerName) && Objects.equals(score, gameScore.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, score);
    }

    public GameScore(String playerName, Integer score) {
       if (playerName == null || playerName.trim().isEmpty() || score == null || score.describeConstable().isEmpty())
        {
            System.out.println("значение не может быть пустым");
            return;
        }
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public int compareTo(@NotNull GameScore o) {
        if (score == null) return 1;
        int r = o.score.compareTo(this.score);
        if (r == 0) {
            r = this.playerName.compareTo(o.playerName);
        }
        return r;

    }

    static void main(String[] args) {
        GameScore g1 = new GameScore(null,0);
        GameScore g2 = new GameScore("null",0);
        System.out.println(g2);
    }
}
