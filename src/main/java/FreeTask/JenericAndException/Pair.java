package FreeTask.JenericAndException;

public class Pair<K,V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }


    static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair(10,"line1 ");
        System.out.println(p1.toString());
    }
}
