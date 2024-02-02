package app.application.ecobikerental.service.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BikeRequest {
    private long idStation;
    private long idType;
}
