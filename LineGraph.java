import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class LineGraph extends JPanel implements MouseMotionListener {
    private double[] data;
    private String xLabel;
    private String yLabel;
    private int padding = 30;
    private int fontSize = 12;
    private String fontName = "Arial";
    private Color lineColor = Color.BLACK;
    private float lineWidth = 2.0f;
    private int xHover = -1;
    private int yHover = -1;
    private int[] xPoints;
    private int[] yPoints;

    public LineGraph(double[] data, String xLabel, String yLabel) {
        this.data = data;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        addMouseMotionListener(this);
    }

    /**
     * This function sets the padding around the graph
     * 
     * @param padding an int value representing the padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }

    /**
     * This function sets the font size of the labels
     * 
     * @param fontSize an int value representing the font size
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * This function sets the font name of the labels
     * 
     * @param fontName a string representing the font name
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    /**
     * This function sets the color of the line graph
     * 
     * @param lineColor a Color object representing the line color
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * This function sets the width of the line graph
     * 
     * @param lineWidth a float value representing the line width
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * This function returns the max value from the data
     * 
     * @param data an array of double representing the data
     * @return a double representing the max value from the data
     */
    public static double highestValue(double[] data) {
        double max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the grid
        g2d.setColor(new Color(230, 230, 230));
        for (int i = padding; i < getWidth() - padding; i += (getWidth() - 2 * padding) / 5) {
            g2d.drawLine(i, padding, i, getHeight() - padding);
        }
        for (int i = padding; i < getHeight() - padding; i += (getHeight() - 2 * padding) / 5) {
            g2d.drawLine(padding, i, getWidth() - padding * 4, i);
        }

        // Draw the line graph
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(lineWidth));
        double max = maxValue(data);
        double min = minValue(data);
        xPoints = new int[data.length];
        yPoints = new int[data.length];
        int yInterval = (int) Math.ceil(max / 5.0);
        int xInterval = (int) Math.ceil(data.length / 5.0);
        for (int i = 0; i < data.length; i++) {
            xPoints[i] = padding + i * (getWidth() - 4 * padding) / (data.length - 1);
            yPoints[i] = (int) (getHeight() - padding
                    - (data[i] - min + 100) * (getHeight() - 2 * padding) / (max - min + 200));
            if (i > 0) {
                g2d.drawLine(xPoints[i - 1], yPoints[i - 1], xPoints[i], yPoints[i]);
            }
        }

        // Draw the x and y axes
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(padding, padding, padding, getHeight() - padding);
        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);

        // Draw the x and y axis intervals
        g2d.setFont(new Font(fontName, Font.PLAIN, fontSize));
        g2d.drawString(xLabel, getWidth() / 2 - g2d.getFontMetrics().stringWidth(xLabel) / 2, getHeight() - 5);
        // ...
        for (int i = 0; i < 5; i++) {
            g2d.drawString(Integer.toString(i * xInterval),
                    padding + i * (getWidth() - 2 * padding) / 5
                            - g2d.getFontMetrics().stringWidth(Integer.toString(i * xInterval)) / 2,
                    getHeight() - padding + fontSize + 5);
        }

        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString(yLabel, -getHeight() / 2 - g2d.getFontMetrics().stringWidth(yLabel) / 2, fontSize + 5);
        for (int i = 0; i < 5; i++) {
            g2d.drawString(Integer.toString(i * yInterval - 100),
                    -padding - i * (getHeight() - 2 * padding) / 5
                            - g2d.getFontMetrics().stringWidth(Integer.toString(i * yInterval - 100)) / 2,
                    -padding + fontSize + 5);
        }
        g2d.setTransform(new AffineTransform());

        if (xHover != -1 && yHover != -1) {
            g2d.setColor(Color.BLUE);
            g2d.setBackground(Color.GRAY);
            g2d.drawString(
                    "Day: " + Integer.toString((xHover - padding) * (data.length - 1) / (getWidth() - 2 * padding)),
                    xHover, yHover - 10);
            g2d.drawString(
                    "Calories: " + Integer.toString((int) ((max - min + 200)
                            - (yHover - padding) * (max - min + 200) / (getHeight() - 2 * padding) + min - 100)),
                    xHover, yHover);
        }
    }

    public static double maxValue(double[] data) {
        double max = data[0];
        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
            if (data[i] < min) {
                min = data[i];
            }
        }
        return max + 100;
    }

    public static double minValue(double[] data) {
        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min - 100;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int closestDistance = 200;
        int closestX = -1;
        int closestY = -1;
        for (int i = 0; i < data.length; i++) {
            int distance = (int) Math.sqrt((e.getX() - xPoints[i]) * (e.getX() - xPoints[i])
                    + (e.getY() - yPoints[i]) * (e.getY() - yPoints[i]));
            if (distance < closestDistance) {
                closestDistance = distance - 10;
                closestX = xPoints[i];
                closestY = yPoints[i];
            }
        }

        if (closestDistance < 5) {
            xHover = closestX;
            yHover = closestY;
            repaint();
        } else {
            xHover = -1;
            yHover = -1;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not needed
    }

}
