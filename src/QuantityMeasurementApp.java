import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= ENUM FOR UNITS =================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ================= QUANTITY CLASS =================
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

        double convertTo(LengthUnit targetUnit) {
            double base = this.toFeet();
            return targetUnit.fromFeet(base);
        }

        // 🔥 UC6: Instance add method
        Quantity add(Quantity other) {
            if (other == null || !Double.isFinite(this.value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException("Invalid input for addition");
            }

            double sumFeet = this.toFeet() + other.toFeet(); // Step 1: base addition
            double resultValue = this.unit.fromFeet(sumFeet); // Step 2: convert to first unit

            return new Quantity(resultValue, this.unit);
        }

        // 🔥 UC6: Static add method
        static Quantity add(Quantity q1, Quantity q2) {
            if (q1 == null || q2 == null) {
                throw new IllegalArgumentException("Null quantity not allowed");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double resultValue = q1.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, q1.unit);
        }

        public String toString() {
            return value + " " + unit;
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

            Quantity q1 = new Quantity(v1, u1);
            Quantity q2 = new Quantity(v2, u2);

            // 🔥 Perform addition
            Quantity result = q1.add(q2);

            System.out.println("Result (in unit of first operand): " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}