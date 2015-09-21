import java.awt.*;

public class PaletteMaker {

    private Color[] colors;             //Current set of Color
    private Palette colorLibrary;       //Instance of additional Palette class
    private int currentPaletteIndex;    //Index of Palette selected by user
    
    public PaletteMaker(int numberOfColors)
    {
        this.currentPaletteIndex = 0;
        this.colorLibrary = new Palette();
        createPalette(numberOfColors);
    }

    public void createPaletteByIndex (int index) {
        this.currentPaletteIndex = index;
        createPalette(colors.length);
    }

    public Color getColorByIndex(int index) {
        if (index<colors.length)
            return colors[index];
        else
            return colors[0];
    }

    public void createPalette (int numberOfColors) {
        switch (currentPaletteIndex) {
            case 1: {
                createPalette1(numberOfColors);
                break;
            }
            case 2: {
                this.colorLibrary = colorLibrary.makeDefaultPalette("Grayscale");
                getColorsFromLib(numberOfColors);
                break;
            }
            case 3: {
                this.colorLibrary = colorLibrary.makeDefaultPalette("EarthSky");
                getColorsFromLib(numberOfColors);
                break;
            }
            case 4: {
                this.colorLibrary = colorLibrary.makeDefaultPalette("HotCold");
                getColorsFromLib(numberOfColors);
                break;
            }
            case 5: {
                this.colorLibrary = colorLibrary.makeDefaultPalette("Fire");
                getColorsFromLib(numberOfColors);
                break;
            }
            case 6: {
                this.colorLibrary = colorLibrary.makeDefaultPalette("CyclicRedCyan");
                getColorsFromLib(numberOfColors);
                break;
            }
            case 7: {
                this.colorLibrary = new Palette(Palette.COLOR_TYPE_RGB);
                colorLibrary.split(0.16);
                colorLibrary.split(0.5);
                colorLibrary.split(0.84);
                colorLibrary.setDivisionPointColorComponents(0, 89f / 255, 50f / 255, 60f / 255);
                colorLibrary.setDivisionPointColorComponents(1, 38f / 255, 1f / 255, 38f / 255);
                colorLibrary.setDivisionPointColorComponents(2, 242f / 255, 238f / 255, 179f / 255);
                colorLibrary.setDivisionPointColorComponents(3, 191f / 255, 175f / 255, 128f / 255);
                colorLibrary.setDivisionPointColorComponents(4, 140f / 255, 105f / 255, 84f / 255);
                getColorsFromLib(numberOfColors);
                break;
            }
            case 8: {
                this.colorLibrary = new Palette(Palette.COLOR_TYPE_RGB);
                colorLibrary.split(0.1);
                colorLibrary.split(0.2);
                colorLibrary.split(0.3);
                colorLibrary.split(0.4);
                colorLibrary.split(0.5);
                colorLibrary.split(0.6);
                colorLibrary.split(0.7);
                colorLibrary.split(0.8);
                colorLibrary.split(0.9);
                colorLibrary.setDivisionPointColorComponents(0, 40f / 255, 6f / 255, 91f / 255);
                colorLibrary.setDivisionPointColorComponents(1, 118f / 255, 3f / 255, 84f / 255);
                colorLibrary.setDivisionPointColorComponents(2, 250f / 255, 1f / 255, 8f / 255);
                colorLibrary.setDivisionPointColorComponents(3, 255f / 255, 47f / 255, 11f / 255);
                colorLibrary.setDivisionPointColorComponents(4, 255f / 255, 117f / 255, 18f / 255);
                colorLibrary.setDivisionPointColorComponents(5, 254f / 255, 255f / 255, 33f / 255);
                colorLibrary.setDivisionPointColorComponents(6, 94f / 255, 178f / 255, 0);
                colorLibrary.setDivisionPointColorComponents(7, 35f / 255, 145f / 255, 21f / 255);
                colorLibrary.setDivisionPointColorComponents(8, 35f / 255, 117f / 255, 53f / 255);
                colorLibrary.setDivisionPointColorComponents(9, 25f / 255, 39f / 255, 140f / 255);
                colorLibrary.setDivisionPointColorComponents(10, 1f / 255, 1f / 255, 1f / 255);
                getColorsFromLib(numberOfColors);
                break;
            }
            default:  // case 0 and other cases
                createPalette0(numberOfColors);
        }
    }

    private void getColorsFromLib(int numberOfColors) {
        this.colors = new Color[numberOfColors];
        for(int i = 0; i < numberOfColors; i++)
        {
            colors[i] = colorLibrary.getColor((double)i/numberOfColors);
        }
    }

    private void createPalette0 (int numberOfColors)
    {
        this.colors = new Color[numberOfColors];
        for(int i = 0; i < numberOfColors; i++)
        {
            colors[i] = Color.getHSBColor((float) i / (float) numberOfColors, 0.85f, 1.0f);
        }
    }

    private void createPalette1 (int numberOfColors) {
        Color color1 = Color.black;
        Color color2 = Color.red;
        this.colors = new Color[numberOfColors];
        for (int i = 0; i < numberOfColors; i++) {
            float ratio = (float)i / (float)colors.length;
            int red = (int)(color2.getRed() * ratio + color1.getRed() * (1 - ratio));
            int green = (int)(color2.getGreen() * ratio +
                    color1.getGreen() * (1 - ratio));
            int blue = (int)(color2.getBlue() * ratio +
                    color1.getBlue() * (1 - ratio));
            colors[i] = new Color(red, green, blue);
        }
    }


}
