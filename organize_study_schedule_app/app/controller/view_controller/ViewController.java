package app.controller.view_controller;

import app.view.splash.SplashBackgroundImg;
import app.view.splash.SplashScreen;

public class ViewController {
    public static void splashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.add(SplashBackgroundImg.getImg());
        splashScreen.setVisible(true);
    }

    public static void main(String[] args) {
        splashScreen();
    }
}
