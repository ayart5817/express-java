package FreeTask.JenericAndException;

public class Box<T> {

    private T x;

    public void setX(T x) {
        this.x = x;
    }
    public T getX() {
        return this.x;
    }
    public <T> void printX() {
        System.out.println(getX());
    }


    static void main(String[] args) {
        Box<String> boxString = new Box<>();
        boxString.setX("Строка");
        boxString.printX();
      
        Box<Integer> boxInt = new Box<>();
        boxInt.setX(123);
        boxInt.printX();
    }

}
