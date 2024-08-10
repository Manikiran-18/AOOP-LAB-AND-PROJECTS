// 1. Single Responsibility Principle (SRP)
class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        switch (employee.getRole()) {
            case "Manager":
                return 5000;
            case "Developer":
                return 4000;
            case "Intern":
                return 1500;
            default:
                return 2000;
        }
    }
}

// 2. Open/Closed Principle (OCP)
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// 3. Liskov Substitution Principle (LSP)
class Bird {
    public void fly() {
        System.out.println("Bird is flying.");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly.");
    }
}

// 4. Interface Segregation Principle (ISP)
interface Worker {
    void work();
}

interface Eater {
    void eat();
}

class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working.");
    }
}

class Human implements Worker, Eater {
    @Override
    public void work() {
        System.out.println("Human is working.");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating.");
    }
}

// 5. Dependency Inversion Principle (DIP)
interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class MyApplication {
    private MessageService messageService;

    public MyApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendNotification(String message) {
        messageService.sendMessage(message);
    }
}

// Main.java - Demonstrating all principles
public class Main {
    public static void main(String[] args) {
        // SRP Example
        Employee emp1 = new Employee("Alice", "Manager");
        Employee emp2 = new Employee("Bob", "Developer");
        SalaryCalculator calculator = new SalaryCalculator();
        System.out.println("Salary of " + emp1.getName() + ": $" + calculator.calculateSalary(emp1));
        System.out.println("Salary of " + emp2.getName() + ": $" + calculator.calculateSalary(emp2));

        // OCP Example
        Shape rectangle = new Rectangle(5, 7);
        Shape circle = new Circle(3);
        System.out.println("Area of Rectangle: " + rectangle.calculateArea());
        System.out.println("Area of Circle: " + circle.calculateArea());

        // LSP Example
        Bird myBird = new Bird();
        myBird.fly();
        Bird myOstrich = new Ostrich();
        try {
            myOstrich.fly(); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        // ISP Example
        Worker robot = new Robot();
        robot.work();
        Human human = new Human();
        human.work();
        human.eat();

        // DIP Example
        MessageService emailService = new EmailService();
        MessageService smsService = new SMSService();
        MyApplication app1 = new MyApplication(emailService);
        app1.sendNotification("Hello via Email!");
        MyApplication app2 = new MyApplication(smsService);
        app2.sendNotification("Hello via SMS!");
    }
}
