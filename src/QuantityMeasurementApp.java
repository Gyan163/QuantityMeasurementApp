public class QuantityMeasurementApp {

    // ================= FEET CLASS =================
    static class Feet {
        double value;

        Feet(double value) {
            this.value = value;
        }

        boolean isEqual(Feet other) {
            return Math.abs(this.value - other.value) < 0.0001;
        }
    }

    // ================= INCHES CLASS =================
    static class Inches {
        double value;

        Inches(double value) {
            this.value = value;
        }

        boolean isEqual(Inches other) {
            return Math.abs(this.value - other.value) < 0.0001;
        }
    }

    // ================= STATIC METHODS =================
    public static boolean compareFeet(double f1, double f2) {
        Feet feet1 = new Feet(f1);
        Feet feet2 = new Feet(f2);
        return feet1.isEqual(feet2);
    }

    public static boolean compareInches(double i1, double i2) {
        Inches inch1 = new Inches(i1);
        Inches inch2 = new Inches(i2);
        return inch1.isEqual(inch2);
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");

        // Hard-coded values (as per UC2 requirement)
        double feetValue1 = 5.0;
        double feetValue2 = 5.0;

        double inchValue1 = 12.0;
        double inchValue2 = 12.0;

        // Compare Feet
        boolean feetResult = compareFeet(feetValue1, feetValue2);
        System.out.println("Feet Equal? " + feetResult);

        // Compare Inches
        boolean inchResult = compareInches(inchValue1, inchValue2);
        System.out.println("Inches Equal? " + inchResult);
    }
}