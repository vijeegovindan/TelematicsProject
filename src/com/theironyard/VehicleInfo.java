package com.theironyard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Vijee on 7/21/17.
 */
public class VehicleInfo {
    int vin;
    double odometerMilesTravelled;
    double consumption;
    double odometerLastOilChange;
    double engineSize;
    double milesPerGallon;
    String timestamp;


    public int getVin() {return vin;}

    public void setVin(int vin) {
        this.vin = vin;
    }

    public double getOdometerMilesTravelled() {
        return odometerMilesTravelled;
    }

    public void setOdometerMilesTravelled(double odometerMilesTravelled) {
        this.odometerMilesTravelled = odometerMilesTravelled;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerLastOilChange() {
        return odometerLastOilChange;
    }

    public void setOdometerLastOilChange(double odometerLastOilChange) {
        this.odometerLastOilChange = odometerLastOilChange;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public double getMilesPerGallon() {
        return this.getOdometerMilesTravelled() / this.getConsumption();
    }

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy hh:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
