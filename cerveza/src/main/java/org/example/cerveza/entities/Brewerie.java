package org.example.cerveza.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "breweries")
public class Brewerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String locatiion;
    private String description;

    @OneToMany(mappedBy = "brewerie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Beer> beers;
}
