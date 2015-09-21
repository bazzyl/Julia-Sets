import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static int DEFAULT_HEIGHT = 480;
    private static int DEFAULT_WIDTH = 640;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Julia Sets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setLocationRelativeTo(null);

        JuliaPanel juliaPanel = new JuliaPanel();
        frame.add(juliaPanel, BorderLayout.CENTER);

            JPanel iterationsPanel = new JPanel();
            iterationsPanel.add(new JLabel("Iterations (10-500)"));
            JTextField iterationsField = new JTextField(3);
            iterationsField.setText(Integer.toString(juliaPanel.juliaFractal.getIterations()));
            iterationsPanel.add(iterationsField);
            JButton iterationsFieldButton = new JButton("Change");
            iterationsFieldButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int newIterations;
                    try {
                        newIterations = Integer.parseInt(iterationsField.getText());
                    } catch (NumberFormatException ex) {
                        newIterations = JuliaFractal.DEFAULT_ITERATIONS;
                    }
                    if (newIterations < 10) newIterations = 10;
                    else if (newIterations > 500) newIterations = 500;
                    juliaPanel.setIterations(newIterations);
                    iterationsField.setText(Integer.toString(newIterations));
                    juliaPanel.repaint();
                }
            });
            iterationsPanel.add(iterationsFieldButton);

        String[] items = {
                "Rainbow Palette",          //Palette 0
                "Black-Red Palette",        //Palette 1
                "Grayscale Palette",        //Palette 2
                "EarthSky Palette",         //Palette 3
                "HotCold Palette",          //Palette 4
                "Fire Palette",             //Palette 5
                "CyclicRedCyan Palette",    //Palette 6
                "Vintage Palette",          //Palette 7
                "Art Palette",              //Palette 8
        };
        JComboBox paletteSelectComboBox = new JComboBox(items);
        paletteSelectComboBox.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           juliaPanel.palette.createPaletteByIndex(paletteSelectComboBox.getSelectedIndex());
                                           juliaPanel.repaint();
                                       }
                                   }
        );

        JButton zoomMinusButton = new JButton("Zoom -");
        zoomMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.zoomOut();
                juliaPanel.repaint();
            }
        });

        JButton zoomPlusButton = new JButton("Zoom +");
        zoomPlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.zoomIn();
                juliaPanel.repaint();
            }
        });

        JPanel zoomPanel = new JPanel (new GridLayout(1,2,0,1));
        zoomPanel.add(zoomMinusButton);
        zoomPanel.add(zoomPlusButton);

        JPanel movingPanel1 = new JPanel(new GridLayout(1,2,0,1));
        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.moveUp();
                juliaPanel.repaint();
            }
        });
        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.moveRight();
                juliaPanel.repaint();
            }
        });
        movingPanel1.add(moveUpButton);
        movingPanel1.add(moveRightButton);

        JPanel movingPanel2 = new JPanel(new GridLayout(1,2,0,1));
        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.moveLeft();
                juliaPanel.repaint();
            }
        });
        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juliaPanel.juliaFractal.moveDown();
                juliaPanel.repaint();
            }
        });
        movingPanel2.add(moveLeftButton);
        movingPanel2.add(moveDownButton);

        JPanel cParameterPanel = new JPanel();
        cParameterPanel.add(new JLabel("Constant = ( "));
        JTextField reCTextFild = new JTextField(5);
        reCTextFild.setText(Double.toString(juliaPanel.juliaFractal.getC().re()));
        cParameterPanel.add(reCTextFild);
        cParameterPanel.add(new JLabel(" , "));
        JTextField imCTextFild = new JTextField(5);
        imCTextFild.setText(Double.toString(juliaPanel.juliaFractal.getC().im()));
        cParameterPanel.add(imCTextFild);
        cParameterPanel.add(new JLabel(" ) "));
        JButton cParameterChangeButton = new JButton("Change");
        cParameterChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double newReC;
                try {
                    newReC = Double.parseDouble(reCTextFild.getText());
                } catch (NumberFormatException ex) {
                    newReC = 0;
                }
                reCTextFild.setText(Double.toString(newReC));
                double newImC;
                try {
                    newImC = Double.parseDouble(imCTextFild.getText());
                } catch (NumberFormatException ex) {
                    newImC = 0;
                }
                imCTextFild.setText(Double.toString(newImC));
                juliaPanel.juliaFractal.setC(new Complex(newReC, newImC));
                juliaPanel.repaint();
            }
        });
        cParameterPanel.add(cParameterChangeButton);

        JPanel topPane = new JPanel();
        topPane.setLayout(new GridLayout(3, 3, 1, 1));
        topPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, 96));
        topPane.add(iterationsPanel);
        topPane.add(zoomPanel);
        topPane.add(cParameterPanel);
        topPane.add(movingPanel1);
        topPane.add(paletteSelectComboBox);
        topPane.add(movingPanel2);

        frame.add(topPane, BorderLayout.NORTH);
        frame.setVisible(true);
        }

    }