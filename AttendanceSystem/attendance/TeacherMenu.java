package attendance;

import java.io.IOException;
import java.util.Scanner;

public class TeacherMenu {
    static Scanner sc = new Scanner(System.in);

    public static void run() throws IOException {
        while (true) {
            System.out.print("Enter Teacher ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Department (ece/cse): ");
            String dept = sc.nextLine();
            System.out.print("Enter Subject (ec/mpmc/evs): ");
            String subject = sc.nextLine();

            Teacher teacher = new Teacher(id, dept, subject);

            System.out.println("Select Option:\n1. View/Edit Attendance Sheet\n2. View Low Attendance Students\n3. Mark Late Attendance");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    AttendanceFunctions.viewEditAttendance(subject);
                    break;
                case 2:
                    AttendanceFunctions.viewLowAttendance(subject);
                    break;
                case 3:
                    AttendanceFunctions.markLateAttendance(subject);
                    break;
                default:
                    System.out.println("Invalid Option");
            }

            System.out.print("Press 1 to continue or 0 to exit: ");
            int cont = sc.nextInt();
            sc.nextLine();
            if (cont == 0) {
                System.out.println("Exiting Teacher Menu...");
                break;
            }
        }
    }
}