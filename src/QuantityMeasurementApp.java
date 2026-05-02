import java.util.Scanner;

public class QuantityMeasurementApp {

    // ================= QUANTITY CLASS =================
    static class Quantity {
        double value;
        LengthUnit unit;

        Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        double toFeet() {
            return unit.convertToBase(value);
        }

        boolean isEqual(Quantity other) {
            return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
        }

        double convertTo(LengthUnit targetUnit) {
            double base = this.toFeet();
            return targetUnit.convertFromBase(base);
        }

        // UC6
        Quantity add(Quantity other) {
            double sumFeet = this.toFeet() + other.toFeet();
            double result = this.unit.convertFromBase(sumFeet);
            return new Quantity(result, this.unit);
        }

        // UC7
        Quantity add(Quantity other, LengthUnit targetUnit) {
            double sumFeet = this.toFeet() + other.toFeet();
            double result = targetUnit.convertFromBase(sumFeet);
            return new Quantity(result, targetUnit);
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
            System.out.print("Enter value 1: ");
            double v1 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter unit 1 (FEET/INCH/YARD/CM): ");
            LengthUnit u1 = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter value 2: ");
            double v2 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter unit 2 (FEET/INCH/YARD/CM): ");
            LengthUnit u2 = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter target unit: ");
            LengthUnit target = LengthUnit.valueOf(sc.nextLine().toUpperCase());

            Quantity q1 = new Quantity(v1, u1);
            Quantity q2 = new Quantity(v2, u2);

            Quantity result = q1.add(q2, target);

            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }

        sc.close();
    }
}