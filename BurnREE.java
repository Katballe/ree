public class BurnREE {
    /**
     * Function to calculate the Resting Energy Expenditure (REE) for burn patients
     * using Xie et. al.'s nonlinear estimation formula
     *
     * @param TBSA         total body surface area affected by burn injury (decimal
     *                     from 0-1)
     * @param postBurnDays the number of days after the burn injury
     * @param BSA          body surface area (m^2)
     * @return the calculated REE
     */
    public static double nonlinearCalculateREE(Patient p) {
        // The formula for REE: REE = (1094.2477 + 7.3670 * TBSA + 22. 3935 *
        // postBurnDays - 0.0766 * TBSA^2 -1.3496 * postBurnDays^2 + 0.4568 * TBSA *
        // postBurnDays) * BSA

        return (1094.2477 + (7.3670 * p.TBSA) + (22.3935 * p.PBD) - (0.0766 * Math.pow(p.TBSA, 2))
                - (1.3496 * Math.pow(p.PBD, 2)) + (0.4568 * p.TBSA * p.PBD)) * p.BSA;
    }

    /**
     * Function to calculate the Resting Energy Expenditure (REE) for burn patients
     * using Xie et. al.'s linear estimation formula
     *
     * @param TBSA total body surface area affected by burn injury (decimal from
     *             0-1)
     * @param PBD  the number of post burn days
     * @param BSA  body surface area (m^2)
     * @return the calculated REE
     */
    public static double linearCalculateREE(Patient p) {
        double REE;
        // Checking if TBSA is less than or equal to 70% and PBD is less than or equal
        // to 14
        if (p.TBSA <= 0.7 && p.PBD <= 14) {
            REE = (1130 + (7 * p.TBSA * 100) + (10 * p.PBD)) * p.BSA;
            // Checking if TBSA is less than or equal to 70% and PBD is greater than 14
        } else if (p.TBSA <= 0.7 && p.PBD > 14) {
            REE = (1330 + (10 * p.TBSA * 100) - (14 * p.PBD)) * p.BSA;
            // Checking if TBSA is greater than 70% and PBD is less than or equal to 14
        } else if (p.TBSA > 0.7 && p.PBD <= 14) {
            REE = (1350 + (0.4 * p.TBSA * 100) - (33 * p.PBD)) * p.BSA;
            // Checking if TBSA is greater than 70% and PBD is greater than 14
        } else {
            REE = (1460 + (2 * p.TBSA * 100) - (12 * p.PBD)) * p.BSA;
        }
        return REE;
    }

}
