/*
 * Compares a user's annual income with the average individual income in Canada.
 *
 * Author: Abdul
 * Date: 2026-10-06
 * Description: Validates income input and reports how it compares to average.
 */
import java.util.Scanner;

/**
 * Runs the Income Checker program.
 */
public final class IncomeChecker {

    private IncomeChecker() {
    }

    /**
     * Reads incomes, compares them with the Canadian average, and repeats if
     * requested.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        // Incomes within 5% of the average count as about average.
        // Average individual income in Canada; source: Statistics Canada
        final double averageIncome = 58000.00;
        final double tolerance = 0.05;
        // Reject values outside this reasonable input range.
        final double minIncome = 0.00;
        final double maxIncome = 10000000.00;
        // These bounds include incomes up to 5% below or above the average.
        final double lowerBound = averageIncome * (1 - tolerance);
        final double upperBound = averageIncome * (1 + tolerance);
        Scanner scanner = new Scanner(System.in);

        // Welcome the user and explain what the program compares.
        System.out.println("Welcome to the Income Checker!");
        System.out.println(
                "This program compares your annual income with the Canadian "
                        + "average.");

        String answer = "";
        // Repeat the check until the user chooses not to continue.
        while (!answer.equals("N")) {
            double income = 0;
            boolean incomeIsValid = false;

            // Keep requesting income until a valid number in range is entered.
            while (!incomeIsValid) {
                System.out.print(
                        "Enter your annual income in CAD "
                                + "($0.00 to $10,000,000.00): ");
                String input = scanner.nextLine();
                try {
                    income = Double.parseDouble(input);
                    // Accept income only when it is within the prompted limits.
                    if (income >= minIncome && income <= maxIncome) {
                        incomeIsValid = true;
                    } else {
                        System.out.println(
                                "Out of range. Enter an income from "
                                        + "$0.00 to $10,000,000.00.");
                    }
                } catch (NumberFormatException exception) {
                    // Non-numeric input is rejected and the prompt is repeated.
                    System.out.println(
                            "Invalid entry. Please enter a number, like "
                                    + "52000 or 61500.50.");
                }
            }

            // Absolute difference is positive whether income is higher or
            // lower.
            double difference = Math.abs(income - averageIncome);
            // Compare against the tolerance bounds to describe the income.
            if (income < lowerBound) {
                System.out.println(
                        "Your income is below the average Canadian income.");
            } else if (income > upperBound) {
                System.out.println(
                        "Your income is above the average Canadian income.");
            } else {
                System.out.println(
                        "Your income is about average for Canada.");
            }

            // Report the average and the difference using currency formatting.
            System.out.println(
                    "The average is "
                            + String.format("$%,.2f", averageIncome)
                            + ". You are "
                            + String.format("$%,.2f", difference)
                            + " away from it.");

            // Accept only uppercase Y or N as the repeat choice.
            answer = "";
            while (!answer.equals("Y") && !answer.equals("N")) {
                System.out.print("Check another income? (Y/N): ");
                answer = scanner.nextLine();
                if (!answer.equals("Y") && !answer.equals("N")) {
                    // Keep asking when the response is not one of the choices.
                    System.out.println(
                            "Invalid entry. Please enter uppercase Y or N.");
                }
            }
        }

        // Close with a message and release the input scanner.
        System.out.println("Goodbye!");
        scanner.close();
    }
}
