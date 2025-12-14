package ComplexTask2.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Move
 * Rating<T extends Number>
 * MovieService Управление Рейтингами Хранение оценок
 *  Хранение оценок в Map<Movie, List<Rating>>
 *  Метод для добавления оценки к фильму, оценка должна быть в пределах от 1 до 10 + потокобезопастность, AVG
 */

// IDe предлагает сразу в Создать в Record — это неизменяемый класс, который автоматически: Создаёт конструктор,Геттеры,equals(),hashCode(),toString().
public class Movie {
   private final String filmName;
   private final String genre;

    public Movie(String filmName, String genre) {
        this.filmName = filmName;
        this.genre = genre;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getGenre() {
        return genre;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(filmName, movie.filmName) && Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmName, genre);
    }
}
