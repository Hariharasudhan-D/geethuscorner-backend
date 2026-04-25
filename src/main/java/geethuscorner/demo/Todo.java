package geethuscorner.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Data

public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Boolean isTrue;


}