package app.application.ecobikerental.service.dto.request;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreditCardRequest {

    private String cardNumber;

    private LocalDate expiredDate;

    private String  cvv;

    private float balance;

    private long idBike;
}
