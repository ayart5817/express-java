package ComplexTask2.Task4;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MovieService  {
    private final Map<Movie, List<Rating<? extends Number>>> ratings = new ConcurrentHashMap<>();

    private double getAverageOrZero(Movie movie) {
        return avgRating(movie).orElse(0.0);
    }

    public synchronized void addRating(Movie movie, Rating<? extends Number> rating) throws  NullPointerException {

        if (rating == null || movie == null) {
            throw new NullPointerException("Фильм и рейтинг не может быть пустым");
        }

        double value = rating.getValue().doubleValue();

        if (value < 0 || value > 10) {
            throw new NullPointerException("Рейтинг должен быть в диапазоне от 0 до 10");
        }

        ratings.computeIfAbsent(movie, k -> new ArrayList<>()).add(rating);

    }
    public OptionalDouble avgRating(Movie movie) throws  NullPointerException {
        if (movie == null) {
            return OptionalDouble.empty();
        }

        List<Rating<? extends Number>> moviesRating = ratings.get(movie);
        if (moviesRating == null) {
            return OptionalDouble.empty();
        }
        return moviesRating.stream()
                .mapToDouble(r -> r.getValue().doubleValue())
                .average();
    }

    public List<Movie> getSortedFilmsByRating() {
        return new ArrayList<>(ratings.keySet()).stream()
                .sorted(Comparator.comparingDouble(this::getAverageOrZero)
                        .reversed())
                        .collect(Collectors.toList());

    }
}
