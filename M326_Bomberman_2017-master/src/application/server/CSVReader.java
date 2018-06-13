package application.server;

import global.utils.PropertyHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public static ArrayList<ArrayList<String>> GetPlaygroundBlueprint(){

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try
        {
            String csvFilePath = PropertyHandler.getProperty("application.playground.blueprint.csv");
            br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                ArrayList<String> row = new ArrayList<String>();
                for(String i: country){
                    row.add(i);
                }
                result.add(row);
            }
        } catch(
                FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(
                IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
