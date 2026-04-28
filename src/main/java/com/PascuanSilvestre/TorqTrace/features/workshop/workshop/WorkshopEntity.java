package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import jakarta.persistence.*;

@Entity
@Table (name = "workshop")
public class WorkshopEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String email;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, length = 20)
    private long phone;
    @Column(nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 200)
    private String city;
    @Column(nullable = false, length = 200)
    private String state;
    @Column(nullable = false, length = 200)
    private String Country;
    private String Status;


}
