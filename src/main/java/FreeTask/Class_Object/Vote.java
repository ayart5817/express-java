package FreeTask.Class_Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vote {
    String voterId;
    String choice;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(voterId, vote.voterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterId);
    }

    public Vote(String voterId, String choice) {
        this.voterId = voterId;
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voterId='" + voterId + '\'' +
                ", choice='" + choice + '\'' +
                '}';
    }

    static void main(String[] args) {
        Vote v1 = new Vote("12", "voice5");
        Vote v2 = new Vote("10", "voice3");
        Vote v3 = new Vote("11", "voice4");
        List<Vote> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.stream().forEach(System.out::println);
    }
}
