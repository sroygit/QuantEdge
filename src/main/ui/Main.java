package ui;

import javax.swing.SwingUtilities;

import model.Portfolio;

// Main class which creates a new instance of QuantEdgeApp class
public class Main {

    // Constructs the main  
    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        splash.showSplash(2000);
        Portfolio portfolio = new Portfolio();

        // Launch the terminal-based UI (QuantEdgeApp) in a separate thread
        new Thread(() -> {
            new QuantEdgeApp(portfolio); // Starts the terminal-based app
        }).start();

        // After the splash screen, launch the main application
        SwingUtilities.invokeLater(() -> {
            new QuantEdgeGUI(portfolio);
        });
    }
}
