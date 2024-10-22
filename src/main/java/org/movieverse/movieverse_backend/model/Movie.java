package org.movieverse.movieverse_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String imageVertical;
    private String name;
    private String description;
    private String type; // Ex: Comedy, Action, etc.
    private BigDecimal price;
    private int duration; // In minutes
    private double rating; // Ex: 7.8
    private String stripeId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
