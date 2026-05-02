import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= ENUM =================
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

        // ================= UC6 (existing) =================
        Quantity add(Quantity other) {
            double sumFeet = this.toFeet() + other.toFeet();
            double result = this.unit.fromFeet(sumFeet);
            return new Quantity(result, this.unit);
        }

        // ================= UC7 (NEW) =================
        Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null ||
                    !Double.isFinite(this.value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException("Invalid input for addition");
            }

            // Step 1: convert both to base
            double sumFeet = this.toFeet() + other.toFeet();

            // Step 2: convert to target unit
            double resultValue = targetUnit.fromFeet(sumFeet);

            // Step 3: return new Quantity in target unit
            return new Quantity(resultValue, targetUnit);
        }

        // ================= STATIC VERSION =================
        static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {

            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double resultValue = targetUnit.fromFeet(sumFeet);

            return new Quantity(resultValue, targetUnit);
        }

        public String toString() {
            return value + " " + unit;
        }
    }

    // ================= MAIN =================
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

            // Target Unit
            System.out.print("Enter target unit (FEET/INCH/YARD/CM): ");
            LengthUnit target = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            Quantity q1 = new Quantity(v1, u1);
            Quantity q2 = new Quantity(v2, u2);

            // 🔥 UC7 Addition
            Quantity result = q1.add(q2, target);

            System.out.println("Result in " + target + ": " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}