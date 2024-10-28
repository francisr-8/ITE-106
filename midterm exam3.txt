import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentGradingSystemFileIOrod {
    public static void main(String[] args) {
        String inputFileName = "student_data.txt";
        String outputFileName = "student_grades.txt";

        try {
            Scanner scanner = new Scanner(new File(inputFileName));


            String[] header = scanner.nextLine().split(",");
            int numAssignments = header.length - 1; // Exclude the "Name" column

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String name = data[0];
                double[] scores = new double[numAssignments];
                for (int i = 1; i < data.length; i++) {
                    scores[i - 1] = Double.parseDouble(data[i]);
                }


                double average = calculateAverage(scores);

                char grade = assignGrade(average);


                writeToFile(outputFileName, name, scores, average, grade);
            }

            scanner.close();

        } 
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFileName);
        }
    }

    private static double calculateAverage(double[] scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    private static char assignGrade(double average) {
        if (average >= 90) {
            return 'A';
        }
        else if (average >= 80) {
            return 'B';
        } 
        else if (average >= 70) {
            return 'C';
        } 
        else if (average >= 60) {
            return 'D';
        } 
        else {
            return 'F';
        }
    }

    private static void writeToFile(String fileName, String name, double[] scores, double average, char grade) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.printf("%-20s\t", name);
            for (double score : scores) {
                writer.printf("%.2f\t", score);
            }
            writer.printf("%.2f\t%s\n", average, grade);
        } 
          catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + fileName);
        }
    }
}