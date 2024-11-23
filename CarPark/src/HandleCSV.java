import java.io.*;
import java.util.Scanner;

public class HandleCSV {

    //Create

    //Read
    public String[] read(String fileType){
        try{
            if(fileType.equals("credentials")){
                System.out.println("fetch credentials csv");

                try {
                    File credentialsFile = new File("CarPark/Credentials.csv");
                    Scanner myReader = new Scanner(credentialsFile);

                    String password = "";
                    String username = "";

                    while(myReader.hasNextLine()){
                        String data = myReader.nextLine();
                        String[] dataArray = data.split(",");

                        username = dataArray[0];
                        password = dataArray[1];

                    }

                    String[] credentials = {username, password};
                    
                    myReader.close();

                    return credentials;
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find Credentials.csv file");
                    System.out.println("Looking in: " + new File("../Credentials.csv").getAbsolutePath());
                }

            } else if(fileType.equals("carpark")){
                System.out.println("fetch carpark csv");

                String[] carpark = {""};

                return carpark;
            } 

        }catch (Exception e){
            e.printStackTrace();
        }

        String[] carpark = {""};
        return carpark;
    }

    //Update

    //Delete
    
}
