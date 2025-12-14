package FreeTask.static_final;

public class Main {
    static void main(String[] args) {

       SecureObject obj =  SecureObject.create();
        System.out.println(obj.getNewObject());
//       SecureObject secureObject = new SecureObject("123"); так создать не можем т.к. нету возможности создавать объект напрямую
//        System.out.println(secureObject.toString());

//        FinalClass finalClass = new FinalClass(); не работает т.к. класс приватный

        FinalClass.printFinalClassDivision();
        FinalClass.printFinalClassMultiplication();
        FinalClass.printFinalClassSubtraction();
        FinalClass.printFinalClassSum();

        Order order1 = new Order();
        System.out.println(order1.getOrderId());
        Order order2 = new Order();
        System.out.println(order2.getOrderId());
        Order order3 = new Order();
        System.out.println(order3.getOrderId());
        Order order4 = new Order();
        System.out.println(order4.getOrderId());
        System.out.println("Текущая версия программы " + ProgramVersion.VERSION);
        Message msg1 = Message.create("Привет ");
        Message msg2 = Message.create(" Мир!!");
        System.out.print(msg1);
        System.out.print(msg2);
    }
}