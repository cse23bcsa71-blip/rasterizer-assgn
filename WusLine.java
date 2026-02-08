import java.awt.*;
import javax.swing.*;

public class WusLine extends JPanel {

    private void plot(Graphics2D g, int x, int y, float brightness) {
        brightness = Math.max(0, Math.min(1, brightness));
        g.setColor(new Color(0f, 0f, 0f, brightness));
        g.fillRect(x, y, 1, 1);
    }

    public void drawWuLine(Graphics2D g, float x0, float y0, float x1, float y1) {

        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);

        if (steep) {
            float temp;
            temp = x0; x0 = y0; y0 = temp;
            temp = x1; x1 = y1; y1 = temp;
        }

        if (x0 > x1) {
            float temp;
            temp = x0; x0 = x1; x1 = temp;
            temp = y0; y0 = y1; y1 = temp;
        }

        float dx = x1 - x0;
        float dy = y1 - y0;
        float gradient = dy / dx;

        // first endpoint
        int xEnd = Math.round(x0);
        float yEnd = y0 + gradient * (xEnd - x0);
        float xGap = 1 - frac(x0 + 0.5f);
        int xPixel1 = xEnd;
        int yPixel1 = (int) Math.floor(yEnd);

        if (steep) {
            plot(g, yPixel1, xPixel1, (1 - frac(yEnd)) * xGap);
            plot(g, yPixel1 + 1, xPixel1, frac(yEnd) * xGap);
        } else {
            plot(g, xPixel1, yPixel1, (1 - frac(yEnd)) * xGap);
            plot(g, xPixel1, yPixel1 + 1, frac(yEnd) * xGap);
        }

        float intery = yEnd + gradient;

        // second endpoint
        xEnd = Math.round(x1);
        yEnd = y1 + gradient * (xEnd - x1);
        xGap = frac(x1 + 0.5f);
        int xPixel2 = xEnd;
        int yPixel2 = (int) Math.floor(yEnd);

        if (steep) {
            plot(g, yPixel2, xPixel2, (1 - frac(yEnd)) * xGap);
            plot(g, yPixel2 + 1, xPixel2, frac(yEnd) * xGap);
        } else {
            plot(g, xPixel2, yPixel2, (1 - frac(yEnd)) * xGap);
            plot(g, xPixel2, yPixel2 + 1, frac(yEnd) * xGap);
        }

        if (steep) {
            for (int x = xPixel1 + 1; x < xPixel2; x++) {
                int y = (int) Math.floor(intery);
                plot(g, y, x, 1 - frac(intery));
                plot(g, y + 1, x, frac(intery));
                intery += gradient;
            }
        } else {
            for (int x = xPixel1 + 1; x < xPixel2; x++) {
                int y = (int) Math.floor(intery);
                plot(g, x, y, 1 - frac(intery));
                plot(g, x, y + 1, frac(intery));
                intery += gradient;
            }
        }
    }

    private float frac(float x) {
        return x - (float)Math.floor(x);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawWuLine(g2, 50, 50, 400, 250);
        drawWuLine(g2, 100, 300, 450, 80);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wu's Line Drawing Algorithm");
        WusLine panel = new WusLine();

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
