/**
 * Represents a Patient with TBSA, PBD, and BSA values
 */
public class Patient {
    // Total Body Surface Area
    public double TBSA;
    // Percentage of Body Degree
    public int PBD;
    // Body Surface Area
    public double BSA;

    /**
     * Constructor to initialize a Patient object
     * 
     * @param TBSA Total Body Surface Area
     * @param PBD  Percentage of Body Degree
     * @param BSA  Body Surface Area
     */
    Patient(double TBSA, int PBD, double BSA) {
        this.TBSA = TBSA;
        this.PBD = PBD;
        this.BSA = BSA;
    }
}
