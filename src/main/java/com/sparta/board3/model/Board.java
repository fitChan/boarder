package com.sparta.board3.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Size(min=2, max = 10)
    private String title;
    @Column(columnDefinition = "TEXT")
    @Size(min=5, max = 400)
    private String content;
    @Size(min=1, max = 10)
    private String author;


}