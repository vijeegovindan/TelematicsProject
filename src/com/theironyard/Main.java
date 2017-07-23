package com.theironyard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

    Scanner scanner = new Scanner(System.in);

    System.out.println("Vehicle information");
    System.out.println("--------------------");
    System.out.println("Enter the Vehicle Identification Number");
    int vinUI = scanner.nextInt();
    System.out.println("Enter the odometer reading for miles travelled");
    double odometerMilesTravelledUI = scanner.nextDouble();
    System.out.println("Enter the consumption");
    double consumptionUI = scanner.nextDouble();
    System.out.println("Enter the odometer reading for last oil change");
    double odometerLastOilChangeUI = scanner.nextDouble();
    System.out.println("Enter the engine size");
    double engineSizeUI = scanner.nextDouble();

    VehicleInfo vehicleinfo = new VehicleInfo();

    vehicleinfo.vin = vinUI;
    vehicleinfo.odometerMilesTravelled = odometerMilesTravelledUI;
    vehicleinfo.consumption = consumptionUI;
    vehicleinfo.odometerLastOilChange = odometerLastOilChangeUI;
    vehicleinfo.engineSize = engineSizeUI;

    TelematicsService.report(vehicleinfo);

    }
}
