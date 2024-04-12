package MPO1_exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Movie {

    private static final List<String> knownCategories = new ArrayList<>();
    private static final List<Movie> extent = new ArrayList<>();
    private final List<String> categories;
    private Long id;
    private String title;
    private Date releaseDate;
    private Rating rating;
    private BigDecimal budget;
    private BigDecimal income;

    public Movie(Long id, String title, Date releaseDate, List<String> categories, Rating rating, BigDecimal budget, BigDecimal income) {
        setId(id);
        setTitle(title);
        setReleaseDate(releaseDate);
        setRating(rating);
        setBudget(budget);
        setIncome(income);

        this.categories = new ArrayList<>();
        if (categories == null || categories.isEmpty()) {
            throw new IllegalArgumentException("Categories cannot be null or empty");
        } else {
            for (String category : categories) {
                addCategory(category);
            }
        }

        addMovie(this);
    }

    public Movie(Long id, String title, Date releaseDate, List<String> categories, Rating rating) {
        this(id, title, releaseDate, categories, rating, null, null);
    }

    public static void addKnownCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        knownCategories.add(category);
    }

    public static void removeKnownCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        knownCategories.remove(category);
    }

    public static List<String> getKnownCategories() {
        return Collections.unmodifiableList(knownCategories);
    }

    public static List<Movie> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    public static void showExtent() {
        System.out.println("Extent of the class: " + Movie.class.getSimpleName());
        for (Movie movie : extent) {
            System.out.println(movie);
        }
    }

    public static List<Movie> findByCategory(String category) {
        return extent.stream()
                .filter(movie -> movie.getCategories().contains(category))
                .toList();
    }

    public static List<Movie> findByRating(Rating rating) {
        return extent.stream()
                .filter(movie -> movie.getRating().equals(rating))
                .toList();
    }

    private static void addMovie(Movie movie) {
        extent.add(movie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) throws IllegalArgumentException {
        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be null");
        }
        this.releaseDate = releaseDate;
    }

    public List<String> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) throws IllegalArgumentException {
        if (rating == null) {
            throw new IllegalArgumentException("Rating cannot be null");
        }
        this.rating = rating;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) throws IllegalArgumentException {
        if (budget.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Budget must be greater than zero");
        }
        this.budget = budget;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal getProfit() {
        if (income == null || budget == null) {
            return null;
        }
        return income.subtract(budget);
    }

    public void setIncome(BigDecimal income) throws IllegalArgumentException {
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Income must be greater than zero");
        }
        this.income = income;
    }

    public void addCategory(String category) throws IllegalArgumentException {
        if (knownCategories.contains(category)) {
            throw new IllegalArgumentException("Category not found in known categories");
        }
        categories.add(category);
    }
}
