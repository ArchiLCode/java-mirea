package org.example.practice16.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor

public class Feedback {
    @Id
    private int id;
    private String name;
    private String description;
    private int product_id;
//    @ManyToOne
//    private Product product;
    public Feedback() {

    }
}