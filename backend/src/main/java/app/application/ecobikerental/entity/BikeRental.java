package app.application.ecobikerental.entity;

import app.application.ecobikerental.service.dto.enums.BikeRentalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "bike_rental")
public class BikeRental {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @Column(name = "total_cost")
    private float totalCost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BikeRentalStatus bikeRentalStatus;

    @Transient
    @Column(name = "rental_duration")
    private long rental_duration;


    public long rental_duration(){
        return endTime.isAfter(startTime)? Duration.between(startTime, endTime).toMinutes():0;
    }



}
