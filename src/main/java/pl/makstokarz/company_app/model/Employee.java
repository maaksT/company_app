package pl.makstokarz.company_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;
}
