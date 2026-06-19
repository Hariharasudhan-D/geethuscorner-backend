package geethuscorner.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data

public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String items;
    private Long total;
}
