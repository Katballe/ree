
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

    }
}
