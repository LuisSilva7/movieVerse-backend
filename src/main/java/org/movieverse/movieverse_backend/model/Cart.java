package org.movieverse.movieverse_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart")
    private List<Movie> movies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        movie.setCart(this);
        updateTotalAmount();
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.setCart(null);
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = movies.stream().map(movie -> {
            BigDecimal moviePrice = movie.getPrice();
            return Objects.requireNonNullElse(moviePrice, BigDecimal.ZERO);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public void clearCart(){
        this.movies.clear();
        updateTotalAmount();
    }
}
