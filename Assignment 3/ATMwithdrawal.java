import java.util.Scanner;

public class ATMwithdrawal {
    private static final String CORRECT_PIN = "1234";
    private static double balance = 3000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.nextLine();
            if (!enteredPin.equals(CORRECT_PIN)) {
                throw new IllegalArgumentException("Invalid PIN.");
            }
            
            System.out.print("Withdraw Amount: ");
            double withdrawAmount = Double.parseDouble(scanner.nextLine());
            if (withdrawAmount > balance) {
                throw new IllegalArgumentException("Insufficient balance. Current Balance: " + balance);
            }
            
            balance -= withdrawAmount;
            System.out.println("Withdrawal successful. Remaining Balance: " + balance);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid amount.");
        } finally {
            System.out.println("Current Balance: " + balance);
            scanner.close();
        }
    }
}
