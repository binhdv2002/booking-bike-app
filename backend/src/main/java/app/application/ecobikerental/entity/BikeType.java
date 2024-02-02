package app.application.ecobikerental.entity;

import app.application.ecobikerental.service.dto.enums.BillType;
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
@Table(name = "bikeType")
public class BikeType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name= "type_name" , columnDefinition = "NVARCHAR(255)")
    private String  typeName;

    @Column(name = "rental_price")
    private float rentalPrice;

    @Column(name = "deposit_price")
    private float depositPrice;

}
