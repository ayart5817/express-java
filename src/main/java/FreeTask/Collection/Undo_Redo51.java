package FreeTask.Collection;

import java.util.*;

public class Undo_Redo51 {
    // Стек для отмены: храним предыдущие состояния pullList
    static Deque<List<String>> undoStack = new ArrayDeque<>();
    // Стек для повтора: храним отменённые действия
    static Deque<String> redoStack = new ArrayDeque<>();

    static List<String> pullList = new LinkedList<>();

    // Сохраняем текущее состояние ДО изменения
    static void saveState() {
        undoStack.push(new LinkedList<>(pullList)); // глубокая копия!
    }

    static void addAction(String str) {
        saveState(); // ← сохраняем ДО добавления
        pullList.add(str);
        redoStack.clear(); // после нового действия redo сбрасывается
        System.out.println("✅ Добавлено: \"" + str + "\"");
    }

    static void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("↩️ Нечего отменять");
            return;
        }
        // Текущее состояние → в redo
        if (!pullList.isEmpty()) {
            redoStack.push(pullList.remove(pullList.size() - 1));
        }
        // Восстанавливаем предыдущее состояние
        pullList = undoStack.pop();
        System.out.println("↩️ Отменено. Текущий список: " + pullList);
    }

    static void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("↪️ Нечего повторить");
            return;
        }
        saveState(); // сохраняем текущее состояние (для возможного повторного undo)
        String action = redoStack.pop();
        pullList.add(action);
        System.out.println("↪️ Повторено: \"" + action + "\" → " + pullList);
    }

    static void show() {
        System.out.println("📋 Список: " + pullList);
    }

    public static void main(String[] args) {
        System.out.println("=== Демонстрация undo/redo ===");
        addAction("Open");
        addAction("Create");
        addAction("Close");
        show(); // [Open, Create, Close]

        undo(); // → [Open, Create]
        undo(); // → [Open]
        undo(); // → []

        redo(); // → [Open]
        redo(); // → [Open, Create]

        addAction("Save"); // → [Open, Create, Save] и redo сброшен!
        show();

        redo(); // → "Нечего повторить", потому что addAction сбросил redo
    }
}