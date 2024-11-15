import java.util.Scanner;

/**.
 * Test class is used to test the LED control system.
 * This class manages processes such as motion, Google Assistant commands, and program exit by taking user input.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Led led = new Led();
        Sensor motionSensor = new Sensor(led);
        ColorCodes colorCodes = new ColorCodes();
        GoogleAssistant googleAssistant = new GoogleAssistant(led, colorCodes, scanner);

        while (true) {
            printMenu();
            System.out.print("Make your choice ('motion', 'hey google', 'show status', or 'exit'): ");
            String userInput = scanner.nextLine().toLowerCase();

            switch (userInput) {
                case "motion":
                    motionSensor.detectMotion();
                    break;
                case "hey google":
                    handleGoogleAssistantMenu(scanner, googleAssistant);
                    break;
                case "show status":
                    led.showStatus();
                    break;
                case "exit":
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * It prints the main menu to the screen.
     */
    private static void printMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Motion status check (motion)");
        System.out.println("2. Google Assistant commands (hey google)");
        System.out.println("3. Show status (show status)");
        System.out.println("4. Exit the program (exit)");
        System.out.println("-----------------------------");
    }

    /**
     * It prints the Google Assistant menu to the screen.
     */
    private static void handleGoogleAssistantMenu(Scanner scanner, GoogleAssistant googleAssistant) {
        while (true) {
            printGoogleAssistantMenu();
            System.out.print("Enter a command ('exit' to exit): ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            googleAssistant.processCommand(command);
        }
    }

    /**
     * It prints the Google Assistant menu to the screen.
     */
    private static void printGoogleAssistantMenu() {
        System.out.println("\n--- GOOGLE ASSISTANT MENU ---");
        System.out.println("1. Turn on the LEDs (turn on led)");
        System.out.println("2. Turn off the LEDs (turn off led)");
        System.out.println("3. Set the brightness (brightness <brightness value>)");
        System.out.println("4. Change color (color <color name>)");
        System.out.println("5. Set sleep mode (time)");
        System.out.println("6. Turn on the LEDs at a specific time (specific time)");
        System.out.println("7. Exit the menu (exit)");
        System.out.println("-----------------------------");
    }
}
