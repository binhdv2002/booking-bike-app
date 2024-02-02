package app.application.ecobikerental.service.dto.enums;

public enum GenderType {
    MALE("male"),
    FEMALE("female");

    GenderType(String value) {
        this.value = value;
    }

    private String value;
   public String getValue(){
       return value.toUpperCase();
   }

}
