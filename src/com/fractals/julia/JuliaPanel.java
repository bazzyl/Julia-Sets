package com.fractals.julia;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaPanel extends JPanel {

    public JuliaFractal juliaFractal;
    public PaletteMaker palette;
    volatile private BufferedImage image;
    volatile private boolean isNeedRedrawImage;
    volatile private boolean isDrawingImage;
    volatile private int progressBarValue;

    public JuliaPanel() {
        super();
        this.juliaFractal = new JuliaFractal(new Complex(-0.374, -0.620), 3);
        this.palette = new PaletteMaker(juliaFractal.getIterations());
        this.isNeedRedrawImage = true;
        this.isDrawingImage = false;
        this.progressBarValue = 0;
        Thread drawBufferedImageThread = new Thread() {
            @Override
            public void run() {
                Graphics2D g2 = null;
                while (true) {
                     if (image == null && getHeight() > 0  && getWidth() > 0) {
                        //Create new image
                        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                        g2 = (Graphics2D)image.getGraphics();
                        isNeedRedrawImage = true;
                    }
                    if (image != null && getHeight() > 0  && getWidth() > 0) {
                        if (image.getHeight() != getHeight() || image.getWidth() != getWidth()) {
                            //Create image with new size
                            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                            g2 = (Graphics2D)image.getGraphics();
                            isNeedRedrawImage = true;
                        }
                        if (isNeedRedrawImage) {
                            isNeedRedrawImage = false;
                            isDrawingImage = true;
                            int pixelsAmount = getWidth() * getHeight();
                            for (int x=0; x <image.getWidth(); x++){
                                //Break current operation if redraw is needed
                                if (isNeedRedrawImage) break;
                                for (int y=0; y<image.getHeight(); y++){
                                    g2.setColor(getColorByCoordinates(x, y));
                                    g2.fillRect(x,y,1,1);
                                    progressBarValue = (x * getHeight() + (y+1)) * 1000 / pixelsAmount;
                                }
                            }
                            isDrawingImage = false;
                        }
                    }
                }
            };
        };
        drawBufferedImageThread.start();
    }

    public synchronized void setIsNeedRedrawImage() {
        this.isNeedRedrawImage = true;
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
        juliaFractal.setScreenSize(getWidth(), getHeight());
        g.drawImage(image, 0, 0, null);
        if (isDrawingImage) {
            g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString("DRAWING...", getWidth()/2-70, getHeight()/2);
            int y0 = getHeight()/2 + 20;
            int x0 = 100;
            int x1 = getWidth() - 100;
            int progressLineLength = (x1 - x0) * progressBarValue /1000;
            g.drawRect(x0 - 2, y0 - 2, x1 - x0 + 4, 7);
            g.drawLine(x0, y0    , x0 + progressLineLength, y0);
            g.drawLine(x0, y0 + 1, x0 + progressLineLength, y0 + 1);
            g.drawLine(x0, y0 + 2, x0 + progressLineLength, y0 + 2);
            g.drawLine(x0, y0 + 3, x0 + progressLineLength, y0 + 3);
        }
    }

}