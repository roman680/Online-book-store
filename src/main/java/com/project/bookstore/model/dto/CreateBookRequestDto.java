package com.project.bookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDto {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String description;
    private String coverImage;
}
