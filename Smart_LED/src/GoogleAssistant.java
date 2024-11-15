import java.util.*;
import java.util.concurrent.TimeUnit;

public class GoogleAssistant {
    private Led leds;
    private Scanner scanner;
    private Timer sleepTimer;
    private ColorCodes colorCodes;
    private Timer turnOnTimer;

    public GoogleAssistant(Led leds, ColorCodes colorCodes, Scanner scanner) {
        this.leds = leds;
        this.colorCodes = colorCodes;
        this.scanner = scanner;
    }

    public void processCommand(String command) {
        CommandProcessor processor = new CommandProcessor(leds, colorCodes, scanner);

        if (command.toLowerCase().contains("turn on") && command.toLowerCase().contains("led")) {
            processor.turnOnLed();
        } else if (command.toLowerCase().contains("turn off")) {
            processor.turnOffLed();
        } else if (command.toLowerCase().startsWith("brightness")) {
            processor.setBrightness(command);
        } else if (command.toLowerCase().startsWith("color")) {
            processor.changeColor(command);
        } else if (command.toLowerCase().startsWith("time")) {
            processor.scheduleSleepMode();
        } else if (command.toLowerCase().startsWith("specific time")) {
            processor.scheduleLedTurnOnAtSpecificTime();
        } else {
            System.out.println("Unknown command");
        }
    }


    private class CommandProcessor {
        private Led leds;
        private ColorCodes colorCodes;
        private Scanner scanner;

        public CommandProcessor(Led leds, ColorCodes colorCodes, Scanner scanner) {
            this.leds = leds;
            this.colorCodes = colorCodes;
            this.scanner = scanner;
        }

        public void turnOnLed() {
            // The operation of turning on the LEDs
            System.out.println("The LEDs are turning on");
            leds.turnOn();
        }

        public void turnOffLed() {
            // The operation of turning off the LEDs
            System.out.println("The LEDs are turning off");
            leds.turnOff();
        }

        public void setBrightness(String command) {
            // The operation of adjusting brightness
            if (!leds.isTurnedOn()) {
                System.out.println("LED is off. Commands can only be used when the LED is on.");
            } else {
                try {
                    String brightnessValue = command.toLowerCase().replace("brightness", "").trim();
                    int brightness = Integer.parseInt(brightnessValue);
                    if (brightness >= 0 && brightness <= 255) {
                        leds.setBrightness(brightness);
                        System.out.println("Brightness will be changed.");
                    } else {
                        System.out.println("Invalid brightness value. Brightness value must be between 0 and 255.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid brightness value.");
                }
            }
        }

        public void changeColor(String command) {
            // The operation of changing the color
            String[] tokens = command.toLowerCase().split(" ");
            if (tokens.length >= 2) {
                String colorName = tokens[1];  // Gets the color name
                if (colorCodes.containsColor(colorName)) {
                    int colorCode = colorCodes.getColorCode(colorName);
                    System.out.println("Selected color: " + colorName + "\nCode: " + colorCode);
                    leds.changeColor(colorName);
                } else {
                    System.out.println("Invalid color name.");
                }
            } else {
                System.out.println("Invalid color command.");
            }
        }

        public void scheduleSleepMode() {
            //The operation of setting the sleep mode.
            if (leds.isTurnedOn()) {
                System.out.print("Enter sleep mode duration (e.g., 5s, 10m, 2h): ");
                try {
                    String sleepDurationInput = scanner.nextLine();
                    int sleepDuration = parseSleepDuration(sleepDurationInput);
                    if (sleepDuration > 0) {
                        scheduleSleepMode(sleepDuration);
                    } else {
                        System.out.println("Invalid time format. Sleep mode not added.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid time format. Sleep mode not added.");
                }
            } else {
                System.out.println("Sleep mode could not be activated. LED is already off.");
            }
        }

        public void scheduleLedTurnOnAtSpecificTime() {
            // The operation of turning on the LEDs at a specific time.
            System.out.print("Enter the time for the LEDs to turn on (e.g., 15:30): ");
            try {
                String timeInput = scanner.nextLine();
                Date specifiedTime = parseTime(timeInput);
                if (specifiedTime != null) {
                    turnOnTimer = new Timer();
                    turnOnTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!leds.isTurnedOn()) {
                                leds.turnOn();  // Turn on the LEDs at the specified time.
                                System.out.println("Scheduled LED turn-on operation completed.");
                            } else {
                                System.out.println("LEDs are already on at the specified time.");
                            }
                        }
                    }, specifiedTime);
                } else {
                    System.out.println("Invalid time format. LED turn-on operation could not be completed.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid time format. LED turn-on operation could not be completed.");
            }
        }

        private int parseSleepDuration(String sleepDurationInput) {
            // The operation of parsing the sleep mode duration
            try {
                char unit = sleepDurationInput.charAt(sleepDurationInput.length() - 1);
                int value = Integer.parseInt(sleepDurationInput.substring(0, sleepDurationInput.length() - 1));

                switch (Character.toLowerCase(unit)) {
                    case 's': // second
                        return value;
                    case 'm': // minute
                        return value * 60;
                    case 'h': // hour
                        return value * 60 * 60;
                    default:
                        return -1; // Invalid unit
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                return -1; // Invalid format
            }
        }

        private void scheduleSleepMode(int sleepDuration) {
            // Sleep mode timer operation.
            sleepTimer = new Timer();
            sleepTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    leds.turnOff();  // Turn off the LEDs after the specified duration.
                    System.out.println("Sleep mode activated. LEDs turned off.");
                }
            }, TimeUnit.SECONDS.toMillis(sleepDuration));
        }

        private Date parseTime(String timeInput) {
            // The operation of parsing a specific time format.
            try {
                String[] timeComponents = timeInput.split(":");
                int hour = Integer.parseInt(timeComponents[0]);
                int minute = Integer.parseInt(timeComponents[1]);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                return calendar.getTime();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                return null; // Invalid time format
            }
        }
    }


}