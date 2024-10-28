import java.util.Scanner;
import java.util.Arrays;

public class StudentGrades2{
    static class Student {
		String name;
		double[] grades;
		double average;
		char letterGrade;

        Student(String name, int numAssignments){
			this.name = name;
			this.grades = new double[numAssignments];
        }
    }

    public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
        
        // Step 1: Array Storage
		System.out.print("Enter the number of students: ");
		int numStudents = scanner.nextInt();
		System.out.print("Enter the number of assignments: ");  // Input for number of assignments
		int numAssignments = scanner.nextInt();
		Student[] students = new Student[numStudents];
        
		// Step 2: Input
		for (int i = 0; i < numStudents; i++){
			System.out.print("Enter the name of student " + (i + 1) + ": ");
			String name = scanner.next();
			students[i] = new Student(name, numAssignments);
            
			for (int j = 0; j < numAssignments; j++){
				double score;
				do{
					System.out.print("Enter score for " + name + ", assignment " + (j + 1) + ": ");
					score = scanner.nextDouble();
				}
				while (score < 0 || score > 100);  // Input validation for score
				students[i].grades[j] = score;
				}
		}
		
		// Step 3: Calculation
		for (Student student : students){
			double sum = 0;
			for (double grade : student.grades){
				sum += grade;
				}
			student.average = sum / numAssignments;
			student.letterGrade = calculateLetterGrade(student.average);
		}
        
		// Step 4: Output
		System.out.printf("\n%-20s %-80s %-10s %-5s%n", "Name", "Scores", "Average", "Grade");
		for (Student student : students){
			System.out.printf("%-20s ", student.name);
			for (double grade : student.grades){
				System.out.printf("%.2f ", grade);
				}
				System.out.printf("%-10.2f %-5c%n", student.average, student.letterGrade);
			}
        
		// Find highest and lowest averages
		double highestAvg = Arrays.stream(students).mapToDouble(s -> s.average).max().orElse(0);
		double lowestAvg = Arrays.stream(students).mapToDouble(s -> s.average).min().orElse(0);
        
		System.out.printf("\nHighest Average Score: %.2f%n", highestAvg);
		System.out.printf("Lowest Average Score: %.2f%n", lowestAvg);
        
		// Sort students by average score
		Arrays.sort(students, (s1, s2) -> Double.compare(s1.average, s2.average));
        
		// Output sorted students
		System.out.println("\nStudents Sorted by Average Score:");
		for (Student student : students) {
			System.out.printf("%-20s %-10.2f %c%n", student.name, student.average, student.letterGrade);
			}
        
		scanner.close();
		}

	private static char calculateLetterGrade(double average) {
		if (average >= 90) return 'A';
		if (average >= 80) return 'B';
		if (average >= 70) return 'C';
		if (average >= 60) return 'D';
		return 'F';
	}
}