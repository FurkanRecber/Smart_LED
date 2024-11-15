import java.util.HashMap;
import java.util.Map;

/**
 * The ColorCodes class contains color codes corresponding to color names.
 * This class is used in the Google Assistant class to check whether a specific color name is valid or not.
 */
public class ColorCodes {
    private Map<String, Integer> colorMap;

    public ColorCodes() {
        colorMap = new HashMap<>();
        initializeColorCodes();
    }

    private void initializeColorCodes() {
        colorMap.put("red", 1);
        colorMap.put("green", 2);
        colorMap.put("blue", 3);
        colorMap.put("white", 4);
        // The color can be added
    }

    public boolean containsColor(String colorName) {
        return colorMap.containsKey(colorName);
    }

    public int getColorCode(String colorName) {
        return colorMap.getOrDefault(colorName, -1);
    }
}
