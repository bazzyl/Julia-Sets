import javax.swing.*;
import java.awt.*;

public class JuliaPanel extends JPanel {

    public JuliaFractal juliaFractal;
    public PaletteMaker palette;

    public JuliaPanel() {
        super();
        this.juliaFractal = new JuliaFractal(new Complex(-0.374, -0.620), 3);
        this.palette = new PaletteMaker(juliaFractal.getIterations());
    }

    public void setIterations(int iterations) {
        this.juliaFractal.setIterations(iterations);
        palette.createPalette(iterations);
    }

    private Color getColorByCoordinates(int x, int y) {
        return palette.getColorByIndex(juliaFractal.getFractalValueByCoordinates(x,y));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        juliaFractal.setScreenSize(getWidth(), getHeight());
        for (int x=0; x <getWidth(); x++){
            for (int y=0; y<getHeight(); y++){
                g2.setColor(getColorByCoordinates(x, y));
                g2.fillRect(x,y,1,1);
            }
        }
    }

}

