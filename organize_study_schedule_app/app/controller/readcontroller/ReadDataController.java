package app.controller.readcontroller;

import app.model.read.ReadData;

public class ReadDataController {
    public static void init() {
        readData();
    }

    public static void readData() {
        ReadData reader = new ReadData();
        reader.readData();
    }
}
