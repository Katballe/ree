import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReeExampleScript {
    public static void main(String[] args) throws Exception {

        // Patient has a 40% total burn surface area, 7 days after the burn injury, and
        // a body surface area of 1.8 m^2
        Patient p = new Patient(0.4, 7, 1.8);

        // Calculating REE using nonlinear formula
        double nonlinearREE = BurnREE.nonlinearCalculateREE(p);
        System.out.println("REE (Nonlinear): " + nonlinearREE); // Output: 2140.35

        // Calculating REE using linear formula
        double linearREE = BurnREE.linearCalculateREE(p);
        System.out.println("REE (Linear): " + linearREE); // Output: 2664.0

        // Example input data
        // double[] data = { 0.1, 0.2, 0.3, 0.4, 0.5 };

        double[] data = createREEArray(15, p);

        String xLabel = "Time (in days)";
        String yLabel = "Calories";

        // Create a JFrame to display the graph
        JFrame frame = new JFrame("Line Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LineGraph lineGraph = new LineGraph(data, xLabel, yLabel);
        lineGraph.setBorder(new EmptyBorder(30, 30, 30, 30));
        frame.add(lineGraph, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Set the background color of the content pane
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Set the title of the frame
        frame.setTitle("Line Graph");

        // Set the frame to be visible
        frame.setVisible(true);
    }

    public static double[] createREEArray(int x, Patient p) {
        double[] REEArray = new double[x];
        for (int i = 0; i < x; i++) {
            Patient patient = new Patient(p.TBSA, i, p.BSA);
            REEArray[i] = round(BurnREE.nonlinearCalculateREE(patient), 1);
            System.out.println(REEArray[i]);
        }
        System.out.println(REEArray[0] + REEArray[2]);
        return REEArray;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
