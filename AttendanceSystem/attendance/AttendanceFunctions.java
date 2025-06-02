package attendance;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AttendanceFunctions {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static Scanner sc = new Scanner(System.in);

    public static void markAttendance(Student student) throws IOException {
        System.out.print("Enter Subject (ec/mpmc/evs): ");
        String subject = sc.nextLine();
        String fileName = subject + "_attendance.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            String timeStamp = LocalDateTime.now().format(dtf);
            bw.write(student.id + "," + student.name + "," + student.dept + "," + timeStamp + "\n");
        }
        System.out.println("Attendance marked successfully.");
    }

    public static void viewOverallAttendance(String id) throws IOException {
        int total = 0, present = 0;
        String[] subjects = {"ec", "mpmc", "evs"};

        for (String subject : subjects) {
            File file = new File(subject + "_attendance.txt");
            if (!file.exists()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    total++;
                    if (line.startsWith(id + ",")) {
                        present++;
                    }
                }
            }
        }
        double percentage = (total == 0) ? 0 : ((double) present / total) * 100;
        System.out.printf("Overall Attendance: %.2f%%\n", percentage);
    }

    public static void viewSubjectAttendance(String id) throws IOException {
        System.out.print("Enter Subject (ec/mpmc/evs): ");
        String subject = sc.nextLine();
        File file = new File(subject + "_attendance.txt");
        if (!file.exists()) {
            System.out.println("No attendance records for this subject.");
            return;
        }

        int total = 0, present = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                total++;
                if (line.startsWith(id + ",")) {
                    present++;
                }
            }
        }
        double percentage = (total == 0) ? 0 : ((double) present / total) * 100;
        System.out.printf("%s Attendance: %.2f%%\n", subject.toUpperCase(), percentage);
    }

    public static void viewEditAttendance(String subject) throws IOException {
        String fileName = subject + "_attendance.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Attendance file does not exist.");
            return;
        }

        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        }

        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }

        System.out.print("Enter line number to edit or 0 to exit: ");
        int lineNo = Integer.parseInt(sc.nextLine());
        if (lineNo > 0 && lineNo <= records.size()) {
            System.out.print("Enter new record: ");
            String newRecord = sc.nextLine();
            records.set(lineNo - 1, newRecord);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String rec : records) {
                bw.write(rec + "\n");
            }
        }
        System.out.println("Attendance updated successfully.");
    }

    public static void viewLowAttendance(String subject) throws IOException {
        String fileName = subject + "_attendance.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Attendance file does not exist.");
            return;
        }

        Map<String, Integer> studentCount = new HashMap<>();
        int totalDays = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalDays++;
                String id = line.split(",")[0];
                studentCount.put(id, studentCount.getOrDefault(id, 0) + 1);
            }
        }

        System.out.println("Students with less than 75% attendance:");
        for (Map.Entry<String, Integer> entry : studentCount.entrySet()) {
            double percent = ((double) entry.getValue() / totalDays) * 100;
            if (percent < 75.0) {
                System.out.printf("ID: %s, Attendance: %.2f%%\n", entry.getKey(), percent);
            }
        }
    }

    public static void markLateAttendance(String subject) throws IOException {
        System.out.print("Enter date (yyyy-MM-dd): ");
        String inputDate = sc.nextLine();
        String fileName = subject + "_attendance.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Attendance file does not exist.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Late Attendance Records:");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String dateTime = parts[3];
                    if (dateTime.startsWith(inputDate)) {
                        String timePart = dateTime.split(" ")[1];
                        if (timePart.compareTo("09:20:00") > 0) {
                            System.out.println(line);
                        }
                    }
                }
            }
        }
    }
}
