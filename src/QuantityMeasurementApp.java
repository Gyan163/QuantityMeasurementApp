import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= GENERIC QUANTITY CLASS =================
    static class Quantity {
        double value;
        String unit;

        // Conversion constants
        static final double INCH_TO_FEET = 1.0 / 12.0;

        Quantity(double value, String unit) {
            this.value = value;
            this.unit = unit.toLowerCase();
        }

        // Convert everything to FEET (base unit)
        double toFeet() {
            if (unit.equals("feet")) {
                return value;
            } else if (unit.equals("inch") || unit.equals("inches")) {
                return value * INCH_TO_FEET;
            } else {
                throw new IllegalArgumentException("Invalid unit: " + unit);
            }
        }

        // Equality check
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

            System.out.print("Enter unit 1 (feet/inches): ");
            String u1 = sc.nextLine();

            // Input 2
            System.out.print("Enter value 2: ");
            double v2 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter unit 2 (feet/inches): ");
            String u2 = sc.nextLine();

            // Create objects
            Quantity q1 = new Quantity(v1, u1);
            Quantity q2 = new Quantity(v2, u2);

            // Compare
            boolean result = q1.isEqual(q2);

            System.out.println("Are quantities equal? " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}