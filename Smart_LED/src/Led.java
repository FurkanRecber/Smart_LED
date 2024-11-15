/**
 * The class "Led" is used to perform LED control operations.
 * It includes basic operations such as turning on and off LEDs, adjusting brightness, and changing colors.
 */
public class Led {
    private boolean turnedOn;
    private int brightness;
    private String currentColor;
    private final String defaultColor = "white"; // Default color

    public Led() {
        this.currentColor = defaultColor; // Sets the default color within the constructor.
    }

    public void turnOn() {
        if (!turnedOn) {
            System.out.println("The request sending to the wled site...");
            // codes for send request to wled
            simulateDelay();
            System.out.println("LEDs turned on");
            brightness = 255;
            turnedOn = true;
        } else {
            System.out.println("The LEDs are already on.");
        }
    }

    public void turnOff() {
        if (turnedOn) {
            System.out.println("The request sending to the wled site...");
            // codes for send request to wled
            simulateDelay();
            System.out.println("LEDs turned off");
            brightness = 0;
            turnedOn = false;
        } else {
            System.out.println("The LEDs are already off.");
        }
    }

    /**
     * Sets the brightness of the LED.
     *
     */
    public void setBrightness(int brightness) {
        if (turnedOn) {
            System.out.println("The request sending to the wled to change led brightness...");
            // codes for send request to wled
            simulateDelay();
            System.out.println("Setting LED brightness to: " + brightness);
            this.brightness = brightness;
        } else {
            System.out.println("Brightness cannot be changed because the LED is off.");
        }
    }

    public void changeColor(String color) {
        if (turnedOn) {
            System.out.println("The request sending to the wled to change led color...");
            // codes for send request to wled
            simulateDelay();
            System.out.println("Changing LED color to: " + color);
            this.currentColor = color; // Change color
        } else {
            System.out.println("The LED is turned off. You can only use commands when the LED is on.");
        }
    }

    public void showStatus() {
        System.out.println("LED status: " + (turnedOn ? "On" : "Off"));
        System.out.println("Brightness: " + brightness);
        System.out.println("Current color: " + currentColor);
    }

    public String getCurrentColor() {
        return currentColor;
    }

    /**
     * Checks whether the LED is on or off.
     *
     * @return If the LED is on, returns true; otherwise, returns false
     */
    public boolean isTurnedOn() {
        return turnedOn;
    }


    private void simulateDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
