# Smart LED Controller (Experimental)

This project is an initial version of a LED control system using Arduino. It is designed with plans to support light modes, brightness control, and color selection features for the LED. **This project is experimental and has not been tested yet.**

## Project Goals

- **Light Modes:** Support various LED patterns like blinking, fading, and steady light.
- **Brightness Control:** Adjust brightness levels for the LED.
- **Color Selection:** Customize RGB LED colors.
- **Voice Control (Future Suggestion):** Intended future integration with Google Assistant or other voice assistants for hands-free control.

## Requirements

This project requires the following hardware and software:

- **Hardware**
  - Arduino (any model with serial capability)
  - RGB LED or individual Red, Green, and Blue LEDs
  - Current-limiting resistors for LEDs
  - Connecting wires and a breadboard

- **Software**
  - Arduino IDE (version 1.8.10 or higher)

## Setup and Installation

1. **Hardware Setup**
   - Connect the RGB LED or separate LEDs to the Arduino's digital pins.
   - Add resistors to protect the LEDs.
   - Ensure a common ground connection in the circuit.

2. **Software Setup**
   - Clone the repository:
     ```bash
     git clone https://github.com/FurkanRecber/Smart_LED.git
     ```
   - Open the `Smart_LED.ino` file in the Arduino IDE.
   - Select your Arduino board and port, and upload the code.

## Notes

- **Experimental Status:** This project is still under development and has not yet been tested. The LED control functionality is in the early stages.
- **Google Assistant Integration:** Integration with Google Assistant is a suggested feature for the future but has not been tested or implemented at this stage.

## Contributing

Feel free to fork this repository or contribute to the project. Suggestions for improvements and contributions for new features are welcome.

## License

This project is open-source and available under the [MIT License](LICENSE).
