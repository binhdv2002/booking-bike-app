package app.application.ecobikerental.service.dto.enums;

public enum BikeRentalStatus {
    PENDING("pending"),
    PAID("paid");

    String value;

    BikeRentalStatus(String value) {
        this.value = value;
    }

    public String getValue(){
        return value.toUpperCase();
    }
}
