import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Panel class to make the initializing of panels be more efficient
public class Panel extends JPanel {
    public Panel(int x, int y, int length, int height){

        // Create border
        Border border = BorderFactory.createLineBorder(Color.BLACK,3);

        this.setLayout(null); // Set layout to null
        this.setBorder(border);
        this.setBounds(x, y, length, height); // Set coordinates and size
    }

}
