import java.util.Calendar;

public class Sensor {
    private boolean motionDetected;
    private Led leds;

    public Sensor(Led leds) {
        this.leds = leds;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void detectMotion() {
        // Motion detection operation.
        if (!leds.isTurnedOn() && isWithinWorkingHours()) {
            System.out.println("Motion Detected!");
            motionDetected = true;
            leds.turnOn();
        } else if (!isWithinWorkingHours()) {
            System.out.println("outside of working hours");
        } else if (leds.isTurnedOn()) {
            System.out.println("Motion detected, but the LED is already on.");
        }
    }

    public void resetMotion() {
        motionDetected = false;
    }

    public void disableSensor() {
        motionDetected = false;
    }
    private boolean isWithinWorkingHours() {
        //we set the operating hours of the sensor from 19:00 to 9:00.
        Calendar now = Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);
        return (!(currentHour >= 9 && currentHour <19));
    }
}