package app.application.ecobikerental.service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalRequest {
    private String barcode;
    private long idUser;
}
