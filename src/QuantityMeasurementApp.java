import java.util.Scanner;

public class QuantityMeasurementApp {

    // Method to check equality
    public static boolean areEqual(double value1, double value2) {
        return value1 == value2;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Quantity Measurement App ===");

        double feet1 = 0;
        double feet2 = 0;

        try {
            // Input
            System.out.print("Enter first value (in feet): ");
            feet1 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter second value (in feet): ");
            feet2 = Double.parseDouble(sc.nextLine());

            // Comparison
            boolean result = areEqual(feet1, feet2);

            // Output
            System.out.println("Are both values equal? " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        }

        sc.close();
    }
}