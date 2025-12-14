package FreeTask.JenericAndException;

import java.util.function.Consumer;

public class Result<T> {
    private final Exception error;
    private final T value;
    private final boolean isShuckses;


    public Result(Exception error, T value, boolean isShuckses) {
        this.error = error;
        this.value = value;
        this.isShuckses = isShuckses;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(null, value, true);
    }

    public static <T> Result<T> failure(Exception error) {
        return new Result<>(error, null, false);
    }

    public boolean isShuckses() {
        return isShuckses;
    }

    public boolean isFailure() {
        return !isShuckses;
    }

    public T get() {
        if (isFailure()) {
            throw new IllegalStateException("ошибка" + error);
        }
        return value;
    }

    public Exception getError() {
        if (isShuckses()) {
            throw new IllegalStateException("нет ошибки в успешном Result");
        }
        return error;
    }

    public void ifSuccess(Consumer<T> consumer) {
        if (isShuckses) {
            consumer.accept(value);
        }
    }
    public void ifFailure(java.util.function.Consumer<Exception> consumer) {
        if (!isShuckses) {
            consumer.accept(error);
        }
    }

    @Override
    public String toString() {
        if (isShuckses) {
            return "Result.success(" + value + ")";
        } else {
            return "Result.failure(" + error.getClass().getSimpleName() + ": " + error.getMessage() + ")";
        }
    }
}