package app.application.ecobikerental.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "bike_station")
public class BikeStation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column
    private int totalBikes;

    @Column(name = "empty_slots")
    private int emptySlots;

    @Column(name= "acreage_park")
    private int acreage_park;

    @Column
    private int capcity;

    @Column(name ="walking_distance")
    private int walking_distance;

}
