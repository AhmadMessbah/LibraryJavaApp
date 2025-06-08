package mft.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@SuperBuilder

public class Loan {
    private int id;
    private Person person;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
