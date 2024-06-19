import javax.swing.*;
import java.awt.*;

// Class to make the label initializing process more efficient
final public class Label extends JLabel {

    String text;
    int x, y, length, height;

    public Label(String text, int x, int y, int length, int height, int fontSize, boolean center){

        this.text = text;
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;

        // Set the alignment of the label to center
        if (center) this.setHorizontalAlignment(JLabel.CENTER);

        this.setBounds(x, y, length, height); // Set coordinates and size
        this.setForeground(new Color(49, 71, 58)); // Set font color
        this.setText(text);
        this.setFont(new Font("Sans Serif", Font.BOLD, fontSize));

    }

}
