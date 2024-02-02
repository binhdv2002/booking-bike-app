package app.application.ecobikerental.service.dto.enums;

public enum BillType {
    PENDING("pending"),
    PAID("paid");

    private String value;

    BillType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }


}
