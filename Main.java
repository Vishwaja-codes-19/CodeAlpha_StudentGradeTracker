import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("       STUDENT GRADE TRACKER");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Class Summary");
            System.out.println("5. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent(scanner, students);
                    break;

                case 2:
                    viewStudents(students);
                    break;

                case 3:
                    searchStudent(scanner, students);
                    break;

                case 4:
                    classSummary(students);
                    break;

                case 5:
                    System.out.println(
                            "\nThank you for using Student Grade Tracker!"
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }

    // Add a new student
    public static void addStudent(
            Scanner scanner,
            ArrayList<Student> students) {

        System.out.println("\n----- Add Student -----");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        double[] marks = new double[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++) {

            while (true) {

                System.out.print(
                        "Enter marks for Subject "
                                + (i + 1) + " (0-100): ");

                double mark = scanner.nextDouble();

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                }

                System.out.println(
                        "Invalid marks! Please enter a value between 0 and 100."
                );
            }
        }

        scanner.nextLine();

        Student student =
                new Student(name, rollNumber, marks);

        students.add(student);

        System.out.println("\nStudent added successfully!");
    }

    // Display all students
    public static void viewStudents(
            ArrayList<Student> students) {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n=================================");
        System.out.println("        ALL STUDENT REPORTS");
        System.out.println("=================================");

        for (Student student : students) {

            System.out.println("\nName        : " + student.name);
            System.out.println(
                    "Roll Number : " + student.rollNumber);

            System.out.printf(
                    "Average     : %.2f%n",
                    student.calculateAverage());

            System.out.println(
                    "Highest Mark: " + student.getHighestMark());

            System.out.println(
                    "Lowest Mark : " + student.getLowestMark());

            System.out.println(
                    "Grade       : " + student.getGrade());

            System.out.println("---------------------------------");
        }
    }

    // Search student by roll number
    public static void searchStudent(
            Scanner scanner,
            ArrayList<Student> students) {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.print(
                "\nEnter roll number to search: ");

        int rollNumber = scanner.nextInt();

        boolean found = false;

        for (Student student : students) {

            if (student.rollNumber == rollNumber) {

                System.out.println("\n----- Student Found -----");

                System.out.println(
                        "Name        : " + student.name);

                System.out.println(
                        "Roll Number : " + student.rollNumber);

                System.out.printf(
                        "Average     : %.2f%n",
                        student.calculateAverage());

                System.out.println(
                        "Highest Mark: "
                                + student.getHighestMark());

                System.out.println(
                        "Lowest Mark : "
                                + student.getLowestMark());

                System.out.println(
                        "Grade       : " + student.getGrade());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent not found.");
        }
    }

    // Display class summary
    public static void classSummary(
            ArrayList<Student> students) {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        double totalAverage = 0;

        Student highestStudent = students.get(0);
        Student lowestStudent = students.get(0);

        for (Student student : students) {

            double average = student.calculateAverage();

            totalAverage += average;

            if (average >
                    highestStudent.calculateAverage()) {

                highestStudent = student;
            }

            if (average <
                    lowestStudent.calculateAverage()) {

                lowestStudent = student;
            }
        }

        double classAverage =
                totalAverage / students.size();

        System.out.println("\n=================================");
        System.out.println("          CLASS SUMMARY");
        System.out.println("=================================");

        System.out.println(
                "Total Students : " + students.size());

        System.out.printf(
                "Class Average  : %.2f%n",
                classAverage);

        System.out.println(
                "Top Student    : "
                        + highestStudent.name
                        + " ("
                        + String.format(
                                "%.2f",
                                highestStudent.calculateAverage())
                        + ")");

        System.out.println(
                "Lowest Student : "
                        + lowestStudent.name
                        + " ("
                        + String.format(
                                "%.2f",
                                lowestStudent.calculateAverage())
                        + ")");
    }
}