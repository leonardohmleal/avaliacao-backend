package br.com.leonardohmleal.avaliacao_final.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 1 , nullable = false)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;


}