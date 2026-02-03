package ui;

import javax.swing.*;
import java.awt.*;

// SpashScreen class which loads a saved logo
public class SplashScreen {
    private JWindow window;
    private String fileLocation;

    //Contructs the SplashScreen Object
    public SplashScreen() {
        // Create the splash screen window
        window = new JWindow();
        fileLocation = "QuantEdgeLogo.png";

        // Load the image
        ImageIcon splashImage = new ImageIcon(fileLocation); 
        JLabel imageLabel = new JLabel(splashImage);
        
        // Add image to the window
        window.getContentPane().add(imageLabel, BorderLayout.CENTER);

        // Set the window size to match the image size
        window.setSize(splashImage.getIconWidth(), splashImage.getIconHeight());

        // Center the splash screen on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = (screenSize.height - window.getHeight()) / 2;
        window.setLocation(x, y);
    }

    /// MODIFIES: this
    // EFFECTS: show the splash screen.
    public void showSplash(int duration) {
        window.setVisible(true);
        
        // Close the splash screen after the specified duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            window.setVisible(false);
            window.dispose();
        }
    }
}

