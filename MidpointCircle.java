import java.awt.*;
import javax.swing.*;

public class MidpointCircle extends JPanel {

    public void drawCircle(Graphics g, int xc, int yc, int r) {

        int x = 0;
        int y = r;
        int p = 1 - r;

        plotCirclePoints(g, xc, yc, x, y);

        while (x < y) {
            x++;

            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }

            plotCirclePoints(g, xc, yc, x, y);
        }
    }

    private void plotCirclePoints(Graphics g, int xc, int yc, int x, int y) {
        g.fillRect(xc + x, yc + y, 2, 2);
        g.fillRect(xc - x, yc + y, 2, 2);
        g.fillRect(xc + x, yc - y, 2, 2);
        g.fillRect(xc - x, yc - y, 2, 2);
        g.fillRect(xc + y, yc + x, 2, 2);
        g.fillRect(xc - y, yc + x, 2, 2);
        g.fillRect(xc + y, yc - x, 2, 2);
        g.fillRect(xc - y, yc - x, 2, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCircle(g, 250, 250, 100);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Circle Drawing Algorithm");
        MidpointCircle panel = new MidpointCircle();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
