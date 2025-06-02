package attendance;

import java.io.IOException;
import java.util.Scanner;

public class AttendanceSystem {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Attendance Management System");
        System.out.print("Are you a Student or Teacher? (Enter 'Student' or 'Teacher'): ");
        String role = sc.nextLine().trim().toLowerCase();

        if (role.equals("student")) {
            StudentMenu.run();
        } else if (role.equals("teacher")) {
            TeacherMenu.run();
        } else {
            System.out.println("Invalid input. Please restart.");
        }
    }
}
