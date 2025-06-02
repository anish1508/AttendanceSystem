package attendance;

import java.io.IOException;
import java.util.Scanner;

public class StudentMenu {
    static Scanner sc = new Scanner(System.in);

    public static void run() throws IOException {
                System.out.print("Enter Student ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Department (ece/cse): ");
            String dept = sc.nextLine();

            Student student = new Student(id, name, dept);

        while (true) {
            System.out.println("Select Option:\n1. Mark Attendance\n2. View Overall Attendance\n3. View Subject-wise Attendance");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    AttendanceFunctions.markAttendance(student);
                    break;
                case 2:
                    AttendanceFunctions.viewOverallAttendance(student.id);
                    break;
                case 3:
                    AttendanceFunctions.viewSubjectAttendance(student.id);
                    break;
                default:
                    System.out.println("Invalid Option");
            }

            System.out.print("Press 1 to continue or 0 to exit: ");
            int cont = sc.nextInt();
            sc.nextLine();
            if (cont == 0) {
                System.out.println("Exiting Student Menu...");
                break;
            }
        }
    }
}
