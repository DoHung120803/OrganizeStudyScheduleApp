package app.controller.view_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import app.view.main.AppUI;
import app.view.splash.SplashBackgroundImg;
import app.view.splash.SplashScreen;

public class ViewController {
    public static void splashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.add(SplashBackgroundImg.getImg());
        splashScreen.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            splashScreen();
            Timer timer = new Timer(4000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    AppUI appUI = new AppUI().init();
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

    }
}
