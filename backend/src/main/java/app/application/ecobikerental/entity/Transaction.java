package app.application.ecobikerental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard ;

    @Column(name = "amount")
    private float amount ;

    @Column(name = "transaction_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime transactionTime ;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description ;

    @Column
    private boolean isReturn;
}
