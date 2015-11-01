package com.fractals.julia;

/**
 *  Julia Fractal Class
 *  f(z) = z^2 + c
*/

public class JuliaFractal {

    public static int DEFAULT_ITERATIONS = 50;
    public static double DEFAULT_FRACTAL_WIDTH = 3;
    public static Complex DEFAULT_C_PARAMETER = new Complex(0.285, 0.01);

    private int screenSizeX;
    private int screenSizeY;
    private Complex c;
    private double dx;
    private double width;
    private double r; //radius
    private int iterations = DEFAULT_ITERATIONS;
    private double x0 = 0;
    private double y0 = 0;

    public JuliaFractal(Complex c, double width) {
        this.width = width;
        this.c =  c;
        this.r = calculateR(c);
    }

    public JuliaFractal() {
        this (DEFAULT_C_PARAMETER, DEFAULT_FRACTAL_WIDTH);
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setScreenSize (int x, int y) {
        this.screenSizeX = x;
        this.screenSizeY = y;
        this.dx = width/screenSizeX;
    }

    public int getFractalValueByCoordinates(int x, int y) {
        return getFractalValue(new Complex(x0 + (x-screenSizeX/2) * dx,  y0 + (-y+screenSizeY/2) * dx));
    }

    public void zoomIn() {
        width = width*0.8;
        this.dx = width/screenSizeX;
    }

    public void zoomOut() {
        width = width/0.8;
        this.dx = width/screenSizeX;
    }

    public void moveRight() {
        x0 += width*0.15;
    }

    public void moveLeft() {
        x0 -= width*0.15;
    }

    public void moveUp() {
        y0 += width*0.15;
    }

    public void moveDown() {
        y0 -= width*0.15;
    }

    public Complex getC() {
        return c;
    }

    public void setC(Complex c) { this.c = c; }

    private int getFractalValue(Complex z) {
        for (int i = 0; i < iterations; i++) {
            if ( z.abs() > r )  return i;
            z = z.times(z).plus(c);
        }
        return 0;
    };

    private static double calculateR(Complex c)
    {
        return (1 + Math.sqrt(1 + 4*c.abs()))/2;
    }

}