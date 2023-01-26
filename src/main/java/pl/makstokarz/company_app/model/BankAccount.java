package pl.makstokarz.company_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Bankaccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
}
