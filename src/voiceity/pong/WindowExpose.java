package voiceity.pong;

import javax.swing.JFrame;
import java.awt.Color;

public class WindowExpose extends JFrame {
    private Content content;

    public WindowExpose() {
        content = new Content();
        this.add(content);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(new Color(0, 0, 0));
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}