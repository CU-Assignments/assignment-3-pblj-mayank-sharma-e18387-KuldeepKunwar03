import java.util.ArrayList;
import java.util.List;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private List<String> enrolledStudents;
    private String prerequisite;

    public Course(String name, int capacity, String prerequisite) {
        this.name = name;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisite = prerequisite;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void enrollStudent(String studentName) throws CourseFullException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("CourseFullException - The course " + name + " is full.");
        }
        enrolledStudents.add(studentName);
    }

    public boolean isEnrolled(String studentName) {
        return enrolledStudents.contains(studentName);
    }
}

class Student {
    private String name;
    private List<String> completedCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new ArrayList<>();
    }

    public void completeCourse(String courseName) {
        completedCourses.add(courseName);
    }

    public boolean hasCompletedCourse(String courseName) {
        return completedCourses.contains(courseName);
    }

    public void enrollInCourse(Course course) throws PrerequisiteNotMetException, CourseFullException {
        if (!course.getPrerequisite().isEmpty() && !hasCompletedCourse(course.getPrerequisite())) {
            throw new PrerequisiteNotMetException("PrerequisiteNotMetException - Complete " + course.getPrerequisite() + " before enrolling in " + course.getName() + ".");
        }
        course.enrollStudent(name);
        System.out.println("Enrolled in course: " + course.getName());
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Course coreJava = new Course("Core Java", 30, "");
        Course advancedJava = new Course("Advanced Java", 30, "Core Java");

        Student student = new Student("John Doe");

        try {
            student.enrollInCourse(advancedJava);
        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Status: " + (student.hasCompletedCourse("Core Java") ? "Core Java completed" : "Core Java not completed"));
    }
}
