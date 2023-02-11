import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        File coursesFile = new File("courses.txt");
        File coursesStudentsFile = new File("courses_students.txt");
        File studentsFile = new File("students.txt");

        Path coursesPath = Paths.get(coursesFile.toURI());
        int coursesLines = 0;
        Path coursesStudentsPath = Paths.get(coursesStudentsFile.toURI());
        int coursesStudentsLines = 0;
        Path studentsPath = Paths.get(studentsFile.toURI());
        int studentsLines = 0;

        try {
            coursesLines = (int) Files.lines(coursesPath).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            coursesStudentsLines = (int) Files.lines(coursesStudentsPath).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            studentsLines = (int) Files.lines(studentsPath).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] coursesData = new String[coursesLines];
        String[] coursesStudentsData = new String[coursesStudentsLines];
        String[] studentsData = new String[studentsLines];

        try {
            Scanner coursesScanner = new Scanner(coursesFile);
            for (int i = 0; i < coursesLines; i++) {
                coursesData[i] = coursesScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner coursesStudentsScanner = new Scanner(coursesStudentsFile);
            for (int i = 0; i < coursesStudentsLines; i++) {
                coursesStudentsData[i] = coursesStudentsScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner studentsScanner = new Scanner(studentsFile);
            for (int i = 0; i < studentsLines; i++) {
                studentsData[i] = studentsScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < studentsLines; i++) {
            String[] iData = studentsData[i].split(",");
            Student student = new Student();
            student.setName(iData[0]);
            student.setAge(Integer.parseInt(iData[1]));
            student.setWeight(Integer.parseInt(iData[2]));
            student.setGroupNum(iData[3]);
            student.setCourse(Integer.parseInt(iData[4]));

            List<Student.Course> courses = new ArrayList<>();

            for (int j = 0; j < coursesStudentsLines; j++) {
                String[] jData = coursesStudentsData[j].split(",");
                Student.Course course = new Student().new Course();
                if (jData[1].equals(student.getName())) {
                    for (int k = 0; k < coursesLines; k++) {
                        String[] kData = coursesData[k].split(",");
                        if (kData[0].equals(jData[0])) {
                            course.setName(kData[0]);
                            course.setDescription(kData[1]);
                            courses.add(course);
                        }
                    }
                }
            }
            student.setCourses(courses);
            students.add(student);
        }
        System.out.println(students);
    }
}