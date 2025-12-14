package FreeTask.Collection;
import java.util.List;

import java.util.*;

public class UniqueCity {
   static Set<String> cities = new HashSet<>(List.of("Москва","Самара", "Ульяновск", "Самара"));
   static final int MAX_SIZE = 5;
   static Queue<String> taskQueue = new PriorityQueue<>(List.of("task1","task2","task3","task4","task5"));


   static void addTask(String task) {
       if (taskQueue.size()>=5) {
           taskQueue.remove();
       } taskQueue.add(task);
   }

    public static void main(String[] args) {
        System.out.println(cities);
        System.out.println(taskQueue);
        UniqueCity.addTask("Task6");
        System.out.println(taskQueue);
    }
}
