import java.awt.*;
import javax.swing.*;

public class DDALine extends JPanel {

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = (float) dx / steps;
        float yInc = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 2, 2);
            x += xInc;
            y += yInc;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawLine(g, 50, 50, 300, 200);
        drawLine(g, 100, 300, 400, 100);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Line Drawing Algorithm");
        DDALine panel = new DDALine();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
