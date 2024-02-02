package app.application.ecobikerental.entity;

import app.application.ecobikerental.service.dto.enums.GenderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.TemporalType.DATE;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

}
