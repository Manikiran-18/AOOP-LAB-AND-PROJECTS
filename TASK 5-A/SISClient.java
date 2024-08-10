import java.util.ArrayList;
import java.util.List;

// Interface for course management
interface ICourseManagement {
    void addStudent(Student student);
    void removeStudent(Student student);
}

// Interface for enrollment management
interface IEnrollmentManagement {
    void enrollStudent(Student student, Course course);
    void disenrollStudent(Student student, Course course);
}

// Student class - Single Responsibility Principle
class Student {
    private String name;
    private String id;
    private List<Course> coursesEnrolled = new ArrayList<>();

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void enrollInCourse(Course course) {
        if (!coursesEnrolled.contains(course)) {
            coursesEnrolled.add(course);
        }
    }

    public void disenrollFromCourse(Course course) {
        coursesEnrolled.remove(course);
    }
}

// Course class - Single Responsibility Principle
class Course implements ICourseManagement {
    private String courseId;
    private String courseName;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.enrollInCourse(this);
        }
    }

    @Override
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
        student.disenrollFromCourse(this);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

// EnrollmentManager class - Handles enrollment management
class EnrollmentManager implements IEnrollmentManagement {
    @Override
    public void enrollStudent(Student student, Course course) {
        course.addStudent(student);
    }

    @Override
    public void disenrollStudent(Student student, Course course) {
        course.removeStudent(student);
    }
}

// Client class to demonstrate the system
public class SISClient {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("Alice", "S001");
        Student student2 = new Student("Bob", "S002");

        // Create courses
        Course course1 = new Course("C001", "Mathematics");
        Course course2 = new Course("C002", "Physics");

        // Create EnrollmentManager
        EnrollmentManager enrollmentManager = new EnrollmentManager();

        // Enroll students in courses
        enrollmentManager.enrollStudent(student1, course1);
        enrollmentManager.enrollStudent(student2, course2);

        // Print enrolled students for each course
        System.out.println("Enrolled students in " + course1.getCourseName() + ":");
        for (Student student : course1.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        System.out.println("Enrolled students in " + course2.getCourseName() + ":");
        for (Student student : course2.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        // Disenroll a student
        enrollmentManager.disenrollStudent(student1, course1);
        System.out.println("\nAfter disenrollment:");
        System.out.println("Enrolled students in " + course1.getCourseName() + ":");
        for (Student student : course1.getEnrolledStudents()) {
            System.out.println(student.getName());
        }
    }
}
