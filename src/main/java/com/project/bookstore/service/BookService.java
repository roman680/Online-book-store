package com.project.bookstore.service;

import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.BookSearchParametersDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto updateBookRequestDto);

    void deleteBook(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
