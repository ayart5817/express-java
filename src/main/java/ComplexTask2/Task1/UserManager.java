package ComplexTask2.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class UserManager<T extends User> {
    private final CopyOnWriteArrayList<T> entities = new CopyOnWriteArrayList<>();

    //Добавление юзера
    public void add(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be Null");
        }
        entities.add(entity);
    }

    //Удаление объекта
    public boolean remove(T entity) {
       if (entity == null) {
           throw new IllegalArgumentException("Entity cannot be Null");
       }
       return entities.remove(entity);

    }

    //Получение всех элементов

    public List<T> getAll() {
        return new ArrayList<>(entities);
    }

    //Фильтрация по возрасту
    public List<T> filterByAge(int min, int max) {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException("Incorrect Min Max value");
        }
       return entities.stream()
                .filter(user -> user.getAge() >= min && user.getAge() <= max)
                .collect(Collectors.toList());

    }

    public List<T> filterByName(String findName) {
        return entities.stream()
                .filter(entity-> entity.getName().equals(findName))
                .toList();
    }

    public List<T> getActiveUsers(boolean active) {
        return entities.stream()
                .filter(entity -> entity.isActive() == active)
                .collect(Collectors.toList());

    }

}
