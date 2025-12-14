package FreeTask.Collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoryDequeue {
    static Deque<String> historyUrl = new ArrayDeque<>();


    static public void historyAdd(String newUrl) {
        historyUrl.push(newUrl);
        System.out.println("Текущая страница URL:" + historyUrl.peek());
    }

    static public void historyDell() {
        if (historyUrl.isEmpty()) {
            System.out.println("Текущая страница отсутствует");
        } else {
            historyUrl.pop();
            System.out.println("Текущая страница URL:" + historyUrl.peek());
        }
    }


    static void main(String[] args) {
        HistoryDequeue.historyDell();
        HistoryDequeue.historyAdd("url1");
        HistoryDequeue.historyAdd("url2");
        HistoryDequeue.historyDell();
    }
}