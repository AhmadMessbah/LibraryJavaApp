package mft.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@SuperBuilder

public class Person {
    private int id;
    private String name;
    private String family;
    private String nationalId;
    private LocalDate birthDate;
}
