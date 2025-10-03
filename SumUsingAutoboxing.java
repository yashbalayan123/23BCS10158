import java.util.Scanner;

public class SumUsingAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Integer sum = 0; // Using Integer to show autoboxing/unboxing

        System.out.println("Enter integers (type 'done' to finish):");

        // Continuously read input and add directly to sum
        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                // Directly add parsed integer value to sum
                sum += Integer.valueOf(input); // Autoboxing + unboxing happening
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        // Final result
        System.out.println("Sum of entered integers: " + sum.intValue());
        sc.close();
    }
}
