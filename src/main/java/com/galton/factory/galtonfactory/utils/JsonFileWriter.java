package com.galton.factory.galtonfactory.utils;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
    public static void writeJsonToFile(String jsonData, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonData);
            System.out.println("JSON exportado correctamente a " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


