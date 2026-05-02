import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= ENUM FOR UNITS =================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0); // cm → inch → feet

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

        // 🔥 UC5: Convert this quantity to target unit
        double convertTo(LengthUnit targetUnit) {
            double baseFeet = this.toFeet();                 // Step 1: to base
            return targetUnit.fromFeet(baseFeet);            // Step 2: to target
        }

        // 🔥 Static conversion method
        static double convert(double value, LengthUnit source, LengthUnit target) {
            if (!Double.isFinite(value) || source == null || target == null) {
                throw new IllegalArgumentException("Invalid input for conversion");
            }

            double baseFeet = source.toFeet(value);
            return target.fromFeet(baseFeet);
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Quantity Measurement App ===");

        try {
            // Input
            System.out.print("Enter value: ");
            double value = Double.parseDouble(sc.nextLine());

            System.out.print("Enter source unit (FEET/INCH/YARD/CM): ");
            LengthUnit source = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter target unit (FEET/INCH/YARD/CM): ");
            LengthUnit target = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            // Convert using static method
            double result = Quantity.convert(value, source, target);

            System.out.println("Converted value: " + result + " " + target);

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit or input!");
        }

        sc.close();
    }
}