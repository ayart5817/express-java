package FreeTask.Collection;

public class ConcatenationString48 {
    static String[] newList = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    static void strCollect(String[] str) {
        String result = "";
        for (String st: str) {
            result = result + ", "  + st;

        } System.out.println(result);
    }
static void strCollect2(String[] str) {
        String result = String.join(", ", str);
    System.out.println(result);
};
    static void main(String[] args) {
        strCollect(newList);
        strCollect2(newList);
    }
}
