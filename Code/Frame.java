import javax.swing.*;
import java.awt.*;

// Class that makes frame initializing more efficient
final public class Frame extends JFrame {

    public Frame() {
        this.setSize(1280, 760); // Set Size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make it so that the frame closes when the close button is clicked
        this.setResizable(false);

        ImageIcon image = new ImageIcon("icon.jpeg"); // Set Icon
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(237, 244, 242)); // Set background color

        this.setVisible(true);
        this.setLayout(null); // Set layout to null
    }
}
