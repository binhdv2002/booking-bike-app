package app.application.ecobikerental.util;

import java.util.Random;

public class Utility {
    public static String randomLicensePlate(){
        Random r = new Random();

        return
                r.nextInt(0,10000)+" - " +
                r.nextInt(0,10000);
    }

    public static String randomBarcode(){
        Random r = new Random();
        return
               String.valueOf( r.nextInt(10000000,100000000));
    }

}
