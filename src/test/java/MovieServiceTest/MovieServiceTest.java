package MovieServiceTest;

import ComplexTask2.Task4.Movie;
import ComplexTask2.Task4.MovieService;
import ComplexTask2.Task4.Rating;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Протестируйте добавление рейтингов, расчёт средней оценки и сортировку фильмов.
 * Проверьте, что оценки валидируются корректно и что фильмы правильно сортируются.
 *
 */
public class MovieServiceTest {

    MovieService movieServiceTest = new MovieService();

    @Test
    public void addRatingValidRatingShouldAddSuccessfully() {
        //Подготовка
        MovieService movieService = new MovieService();
        Movie movie1 = new Movie("Terminator", "Action");
        Rating<Double> rating1 = new Rating<>(8.5);
        Rating<Double> rating3 = new Rating<>(9.5);
        Movie movie2 = new Movie("Inception", "Fantastic");
        Rating<Double> rating2 = new Rating<>(9.5);

        //Добавление рейтинга к фильму (здесь могут быть исключения)
        movieService.addRating(movie1,rating1);
        movieService.addRating(movie1,rating3);
        movieService.addRating(movie2,rating2);

        // Проверка Assert на то что есть рейтинг и что средний считается верно
        OptionalDouble avg = movieService.avgRating(movie1);
        assertTrue(avg.isPresent(), "Рейтинг успешно добавлен");
        assertEquals(9, avg.getAsDouble(), "Средний рейтинг считается верно и равен 9.0");


    }

    @Test
    public void testSameMovieGetsCombinedRatings() {
        MovieService service = new MovieService();

        // Два разных объекта, но один фильм проверка реализации hasCode & Equals
        Movie m1 = new Movie("Inception", "Action");
        Movie m2 = new Movie("Inception", "Action");

        service.addRating(m1, new Rating<>(9.0));
        service.addRating(m2, new Rating<>(8.0));

        // Должно быть 1 фильм с двумя оценками!
        OptionalDouble avg = service.avgRating(m1);
        assertTrue(avg.isPresent());
        assertEquals(8.5, avg.getAsDouble(), 0.001, "проверка того что авг верно рассчитано для 2-х фильмов с одинаковым названием");

    }

    @Test
    // чек валидации (Invalid
    public void invalidRatingCheck() {
        Movie m1 = new Movie("Sobat", "Opera");
        movieServiceTest.addRating(m1,new Rating<>(0)); // успешно
        assertThrows(NullPointerException.class, () ->  movieServiceTest.addRating(m1,new Rating<>(-1)),"Ошибка на -1 рейтинг");
        assertThrows(NullPointerException.class, () -> movieServiceTest.addRating(m1, new Rating<>(10.01)), "Ошибка  на 10.01 рейтинга");


    }

    @Test
    public void checkSortFilmByRating() {
        Movie m1 = new Movie("Game of the thrones","serial");
        Movie m3 = new Movie("Inception", "Action");
        Movie m2 = new Movie("Inception", "Action");

        movieServiceTest.addRating(m3, new Rating<>(8.0));
        movieServiceTest.addRating(m2, new Rating<>(8.0));
        movieServiceTest.addRating(m1,new Rating<Integer>(10));
        movieServiceTest.addRating(m3, new Rating<>(6.0));
        movieServiceTest.addRating(m2, new Rating<>(10.0));
        movieServiceTest.addRating(m1,new Rating<Integer>(10));
        List<Movie> sortedMovies = movieServiceTest.getSortedFilmsByRating();
        assertEquals(m1, sortedMovies.getFirst()); // m1 - должен быть первым в списке
        assertEquals(m3, sortedMovies.getLast()); // m3 - должен быть третьим в списке
        assertEquals(2, sortedMovies.size()); // 2 фильма т.к. Совпадает filmName
    }
}
