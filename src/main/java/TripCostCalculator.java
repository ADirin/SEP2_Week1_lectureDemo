import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TripCostCalculator {
    public static double calculateTripCost(double kilometers, double fuelPrice, double fuelConsumptionPer100Km) {
        double fuelNeeded = (kilometers / 100) * fuelConsumptionPer100Km;
        return fuelNeeded * fuelPrice;
    }

    public static void main(String[] args) {
        System.out.println("Select a language:");
        System.out.println("1. Farsi");
        System.out.println("2. Finnish");
        System.out.println("3. Japanese");

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine().trim());

        Locale locale;
        switch (choice) {
            case 1:
                locale = new Locale("fa", "IR");
                break;
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("ja", "JP");
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                locale = new Locale("en", "US");
                break;
        }

        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("messagesa", locale);
        } catch (Exception e) {
            System.out.println("Error loading language pack. Defaulting to English.");
            rb = ResourceBundle.getBundle("messagesa", new Locale("en", "US"));
        }

        // Ask for input
        System.out.print(rb.getString("kilo"));
        double kilometers = Double.parseDouble(scanner.nextLine().trim());

        System.out.print(rb.getString("fuel"));
        double fuelPrice = Double.parseDouble(scanner.nextLine().trim());

        double fuelConsumptionPer100Km = 5.0;
        double totalCost = calculateTripCost(kilometers, fuelPrice, fuelConsumptionPer100Km);

        // Use MessageFormat to replace {0} with the total cost
        String totalMessage = MessageFormat.format(rb.getString("total"), String.format("%.2f", totalCost));
        System.out.println(totalMessage);

        scanner.close();
    }
}
