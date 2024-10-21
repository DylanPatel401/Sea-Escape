package components;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Database {
    private static final String FILENAME = "database.txt";

    public static void main(String[] args) {
    	initialVariables();

    }

    private static void initialVariables() {
        String[] button_ids = {"sprit_yellow", "sprit_red", "sprit_purple", "sprit_green", "sprit_blue", "sprit_black"};
        for(int i = 0; i < button_ids.length; i++) {
        	if(!variableExists(button_ids[i])) {
        		if(button_ids[i] == "sprit_yellow") {
            		writeToFile(button_ids[i], "true");            		
        		}else {
	        		writeToFile(button_ids[i], "false");   
	        		
        		}
        	}
                	
        }
        if(!variableExists("shells")) writeToFile("shells", "0");
        if(!variableExists("FISH_URL")) writeToFile("FISH_URL", "https://i.ibb.co/CQp02H2/Fish.gif");   	
    }
    
    private static void writeToFile(String variableName, String variableValue) {
        boolean variableFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            StringBuilder fileContents = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.startsWith(variableName + "=")) {
                    variableFound = true;
                    break;
                }
                fileContents.append(line).append(System.lineSeparator());
            }
            if (!variableFound) {
                fileContents.append(variableName).append("=").append(variableValue).append(System.lineSeparator());
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
                    bw.write(fileContents.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean variableExists(String variableName) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(variableName + "=")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateVariable(String variableName, String newValue) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            StringBuilder fileContents = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.startsWith(variableName + "=")) {
                    line = variableName + "=" + newValue;
                }
                fileContents.append(line).append(System.lineSeparator());
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
                bw.write(fileContents.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public String readFromFile(String variableName) {
        String variableValue = null;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(variableName + "=")) {
                    variableValue = line.substring(variableName.length() + 1);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variableValue;
    }
}
