package mft.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@SuperBuilder

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
}
