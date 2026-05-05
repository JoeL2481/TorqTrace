package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name = "workshop")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WorkshopEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false, length = 255)
    private String name;
    @Column(name="email",nullable = false, length = 255)
    private String email;
    @Column(name="descripcion",nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name="phone",nullable = false, length = 20)
    private long phone;
    @Column(name="addres",nullable = false, length = 200)
    private String address;
    @Column(name="city",nullable = false, length = 200)
    private String city;
    @Column(name="state",nullable = false, length = 200)
    private String state;
    @Column(name="country",nullable = false, length = 200)
    private String Country;
    @Column(name="status",nullable = false)
    private boolean status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
