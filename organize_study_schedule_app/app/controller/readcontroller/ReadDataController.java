package app.controller.readcontroller;

import app.model.read.ReadData;

public class ReadDataController {
    public void init() {
        readData();
    }

    public void readData() {
        ReadData reader = new ReadData();
        reader.readData();
    }

    public static void main(String[] args) {
        System.out.println("test git");
    }

}
