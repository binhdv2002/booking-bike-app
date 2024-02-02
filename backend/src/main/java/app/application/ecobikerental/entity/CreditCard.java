package app.application.ecobikerental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expired_date")
    @Temporal(TemporalType.DATE)
    private LocalDate expiredDate;

    @Column(name= "cvv")
    private String  cvv;


    @Column(name= "balance")
    private float balance;

    public CreditCard() {
        this.balance = 1000000;
    }
}
