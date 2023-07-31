package com.jumarket.domain
import jakarta.persistence.*;

@Entity
@Table(name = "terminal")
class Terminal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;
}


