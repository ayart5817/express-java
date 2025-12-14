package FreeTask.Collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommentsCountArticle {
    static Map<String, Integer> myMap = new HashMap<>();
    static void addArticle(String article) {
        if (article.trim().isEmpty()) {
            System.out.println("нельзя добавить пустую статью");
            return;
        };
        myMap.put(article, myMap.getOrDefault(article, 0) +1);
    };
    static void getCountArticle(String article) {
        System.out.println(myMap.get(article));
    }
    static void getAll() {
        if (myMap.isEmpty()) {
            System.out.println("нет записей");
            return;
        }
        for (Map.Entry<String, Integer> entry: myMap.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
    }

    static void main(String[] args) {
        CommentsCountArticle.addArticle("Статья 1 ");
        CommentsCountArticle.addArticle("Статья 1 ");
        CommentsCountArticle.addArticle("Статья 1 ");
        CommentsCountArticle.addArticle("Статья 2 ");
        CommentsCountArticle.addArticle("Статья 2 ");
        CommentsCountArticle.addArticle("Статья 3 ");
        CommentsCountArticle.getCountArticle("Статья 1 ");
        CommentsCountArticle.getAll();
    }
}
