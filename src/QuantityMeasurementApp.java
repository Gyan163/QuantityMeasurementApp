import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= ENUM FOR UNITS =================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);  // cm → inch → feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ================= GENERIC QUANTITY CLASS =================
    static class Quantity {
        double value;
        LengthUnit unit;

        Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        double toFeet() {
            return unit.toFeet(value);
        }

        boolean isEqual(Quantity other) {
            return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Quantity Measurement App ===");

        try {
            // Input 1
            System.out.print("Enter value 1: ");
            double v1 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter unit 1 (FEET/INCH/YARD/CM): ");
            LengthUnit u1 = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            // Input 2
            System.out.print("Enter value 2: ");
            double v2 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter unit 2 (FEET/INCH/YARD/CM): ");
            LengthUnit u2 = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            // Create objects
            Quantity q1 = new Quantity(v1, u1);
            Quantity q2 = new Quantity(v2, u2);

            // Compare
            boolean result = q1.isEqual(q2);

            System.out.println("Are quantities equal? " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit entered!");
        }

        sc.close();
    }
}