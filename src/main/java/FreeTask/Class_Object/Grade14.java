package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Grade14 implements Comparable<Grade14>{
    String studentName;
    String subject;
    int score;

    public Grade14(String studentName, String subject, int score) {
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade14{" +
                "studentName='" + studentName + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grade14 grade14 = (Grade14) o;
        return score == grade14.score && Objects.equals(studentName, grade14.studentName) && Objects.equals(subject, grade14.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, subject, score);
    }

    @Override
    public int compareTo(@NotNull Grade14 o) {
        return Integer.compare(this.score, o.score);


    }


    static void main(String[] args) {
        Grade14 g1 = new Grade14("albert", "history", 9);
        Grade14 g2 = new Grade14("artem", "history", 60);
        Grade14 g3 = new Grade14("artem", "biology", 60);
        Grade14 g4 = new Grade14("martin", "biology", 65);
        System.out.println(g1.equals(g3)); // true
        System.out.println(g1.compareTo(g2)); // >0 → g1 "больше" g2 (95 > 85)
    }
}
