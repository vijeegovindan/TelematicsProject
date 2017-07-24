package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Vijee on 7/21/17.
 */

public class TelematicsService {

    static void report(VehicleInfo vehicleInfo) {

        // Create the template file -  template.html
        Path TEMPLATE = Paths.get( "template.html" ).toAbsolutePath();

        try {
            String avgVehicleData = "";
            String vehicleHistory = "";
            String HTMLStringContent = "";
            String HTMLContent = "";
            int noOfFiles = 0;
            double OdometerMilesTravelled = 0;
            double Consumption = 0;
            double OdometerLastOilChange = 0;
            double EngineSize = 0;

            // Create a new Vehicle Info file
            File jsonFile = new File( "" + vehicleInfo.getVin() + ".json" );

            // Write the new Vehicle Info file
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue( jsonFile, vehicleInfo );

            //Read the template HTML file
            try {
                BufferedReader bufferedreader = new BufferedReader( new FileReader( TEMPLATE.toString() ) );
                while ((HTMLStringContent = bufferedreader.readLine()) != null) {
                    HTMLContent = HTMLContent + HTMLStringContent;
                }
                bufferedreader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Read all the JSON files and write it to the output file
            File allJsonFiles = new File( "." );
            ObjectMapper objMapper = new ObjectMapper();
            for (File f : allJsonFiles.listFiles()) {
                if (f.getName().endsWith( ".json" )) {
                    try {
                        noOfFiles += 1;
                        String jsonVehicle = "";
                        String JSONContent = "";

                        // read each JSON file
                        try {
                            BufferedReader jsonBufferedreader = new BufferedReader( new FileReader( f ) );
                            while ((jsonVehicle = jsonBufferedreader.readLine()) != null) {
                                JSONContent = JSONContent + jsonVehicle;
                            }

                            VehicleInfo vi = objMapper.readValue( JSONContent, VehicleInfo.class );

                            //Average Calculations
                            OdometerMilesTravelled = (OdometerMilesTravelled + vi.getOdometerMilesTravelled());
                            Consumption = (Consumption + vi.getConsumption());
                            OdometerLastOilChange = (OdometerLastOilChange + vi.getOdometerLastOilChange());
                            EngineSize = (EngineSize + vi.getEngineSize());

                            // Construct the history data
                            vehicleHistory = vehicleHistory + "<tr><td align=\"center\">" + vi.getVin() + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" + String.format( "%.2f", vi.getOdometerMilesTravelled() ) + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" + String.format( "%.2f", vi.getConsumption() ) + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" + String.format( "%.2f", vi.getOdometerLastOilChange() ) + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" + String.format( "%.2f", vi.getEngineSize() ) + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" + String.format( "%.2f", vi.getMilesPerGallon()) + "</td>";
                            vehicleHistory = vehicleHistory + "<td align=\"center\">" +  vi.getTimestamp() + "</td></tr>";

                            jsonBufferedreader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Average - HTML Section
            avgVehicleData = avgVehicleData + "<tr><td align=\"center\">" + String.format( "%.2f", OdometerMilesTravelled / noOfFiles ) + "</td>";
            avgVehicleData = avgVehicleData + "<td align=\"center\">" + String.format( "%.2f", Consumption / noOfFiles ) + "</td>";
            avgVehicleData = avgVehicleData + "<td align=\"center\">" + String.format( "%.2f", OdometerLastOilChange / noOfFiles ) + "</td>";
            avgVehicleData = avgVehicleData + "<td align=\"center\">" + String.format( "%.2f", EngineSize / noOfFiles ) + "</td></tr>";

            //Construct the DASHBOARD HTML Content
            HTMLContent = HTMLContent.replace( "[average]", "" + noOfFiles + "" );
            HTMLContent = HTMLContent.replace( "[avgVehicleData]", "" + avgVehicleData + "" );
            HTMLContent = HTMLContent.replace( "[history]", "" + vehicleHistory + "" );


            // Write to Dashboard.html
            try {
                FileWriter filewriter = new FileWriter( "dashboard.html" );
                BufferedWriter writer = new BufferedWriter( filewriter );
                writer.write( HTMLContent );
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

