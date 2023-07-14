import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;

  public class MeanMedianMode{
    public static void main(String[] args) {
        JFrame window = new JFrame("Rosales Mean, Meadian, Mode Statistics");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        window.setResizable(false);

        JLabel inputLabel = new JLabel("Enter numbers: (separated by spaces)");
        JTextField inputField = new JTextField(20);
        JButton calculateButton = new JButton("Calculate");
        JTextArea outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(inputLabel, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(calculateButton, BorderLayout.SOUTH);
        window.add(panel, BorderLayout.CENTER);
        window.add(scrollPane, BorderLayout.EAST);
    
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                String[] inputArray = input.split(" ");
                ArrayList<Double> values = new ArrayList<Double>();
                for (String s : inputArray) {
                    if (s.equals("-100")) {
                        break;
                    }
                    try {
                        double value = Double.parseDouble(s);
                        if (value >= -1000 && value <= 1000) {
                            values.add(value);
                        } else {
                            JOptionPane.showMessageDialog(null, "Input value must be between -1000 and 1000.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input: " + s);
                    }
                }

                if (values.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No valid input values entered.");
                    return;
                }

                double[] valuesArray = new double[values.size()];
                for (int i = 0; i < values.size(); i++) {
                    valuesArray[i] = values.get(i);
                }

    
                // Calculate mean, median, mode, maximum and minimum for each set of numbers
                double mean = calculateMean(valuesArray);
                double median = calculateMedian(valuesArray);
                double mode = calculateMode(valuesArray);
                double max = calculateMax(valuesArray);
                double min = calculateMin(valuesArray);

                String output = "Input values: " + Arrays.toString(valuesArray) + "\n\n" +
                        "Mean: " + mean + "\n" + "Median: " + median + "\n" + "Mode: " + mode
                        + "\n" + "Maximum: " + max + "\n" + "Minimum: " + min;

                outputArea.setText(output);
            }
        });
    
        window.pack();
        window.setVisible(true);
    }
    
    //mean
    public static double calculateMean(double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }
    
    //median
    public static double calculateMedian(double[] values) {
        Arrays.sort(values);
        int middleIndex = values.length / 2;
        if (values.length % 2 == 0) {
            return (values[middleIndex - 1] + values[middleIndex]) / 2;
        } else {
            return values[middleIndex];
        }
    }
    
    //mode
    public static double calculateMode(double[] values) {
        Arrays.sort(values);
        double mode = values[0];
        int count = 1;
        int maxCount = 1;
        for (int i = 1; i < values.length; i++) {
            if (values[i] == values[i - 1]) {
                count++;
            } else {
                if (count > maxCount) {
                    mode = values[i - 1];
                    maxCount = count;
                }
                count = 1;
            }
        }
        if (count > maxCount) {
            mode = values[values.length - 1];
        }
        return mode;
    }
    
    //maximum value
    public static double calculateMax(double[] values) {
        double max = values[0];
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    
    //minimum value
    public static double calculateMin(double[] values) {
        double min = values[0];
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;
       }
    
}

/*Most of the Code were recycled from the Activity Mean Median Mode.
Accept any number of numbers, int, float or double between -1000 to 1000.
When the user inputs -1000, then stop getting anymore numbers.
Create a generic class which accepts all 3 numerical data types with graphical user interface.
Calculate the mean, median, mode, standard deviation and variance of the given numerical set. */