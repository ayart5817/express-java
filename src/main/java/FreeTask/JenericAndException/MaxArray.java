package FreeTask.JenericAndException;

public class MaxArray<T extends Comparable<T>> {
    public static <T extends Comparable<T>> T maxArray(T[] array) {
        T result = array[0];
        for (T e : array) {
            if (e.compareTo(result) < 0) {
                result = e;
            }
        }
        return result;
    }

    static void main(String[] args) {
        String[] arr = {"n","adad", "adadadad", "fegw","addad"};
        System.out.println(maxArray(arr));
    }
}
