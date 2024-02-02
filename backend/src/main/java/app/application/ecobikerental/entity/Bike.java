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
@Table(name = "bike")
public class Bike {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private BikeStation bikeStation;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BikeType bikeType;

    @Column(name = "battery_level")
    private int batteryLevel;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "barcode")
    private String barcode;

    @Column(name ="status_bike")
    private boolean status;

}
