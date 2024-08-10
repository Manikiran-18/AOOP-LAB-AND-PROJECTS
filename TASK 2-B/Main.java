// Singleton class to manage user authentication
class UserAuthentication {
    private static UserAuthentication instance;
    private String authenticatedUser;

    // Private constructor to prevent instantiation
    private UserAuthentication() {
        authenticatedUser = null;
    }

    // Public method to provide access to the single instance of UserAuthentication
    public static UserAuthentication getInstance() {
        if (instance == null) {
            synchronized (UserAuthentication.class) {
                if (instance == null) {
                    instance = new UserAuthentication();
                }
            }
        }
        return instance;
    }

    // Method to log in the user
    public void login(String username) {
        if (authenticatedUser == null) {
            authenticatedUser = username;
            System.out.println(username + " has logged in successfully.");
        } else {
            System.out.println("User " + authenticatedUser + " is already logged in.");
        }
    }

    // Method to log out the user
    public void logout() {
        if (authenticatedUser != null) {
            System.out.println(authenticatedUser + " has logged out successfully.");
            authenticatedUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Method to check if a user is authenticated
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    // Method to get the authenticated user
    public String getAuthenticatedUser() {
        return authenticatedUser;
    }
}

// Abstract Vehicle class
abstract class Vehicle {
    public abstract void ride();
}

// Concrete classes for different types of vehicles
class Car extends Vehicle {
    public void ride() {
        System.out.println("Riding in a Car.");
    }
}

class Bike extends Vehicle {
    public void ride() {
        System.out.println("Riding on a Bike.");
    }
}

class Scooter extends Vehicle {
    public void ride() {
        System.out.println("Riding on a Scooter.");
    }
}

// VehicleFactory class to create different types of vehicles
class VehicleFactory {
    public Vehicle createVehicle(String vehicleType) {
        switch (vehicleType) {
            case "Car":
                return new Car();
            case "Bike":
                return new Bike();
            case "Scooter":
                return new Scooter();
            default:
                return null;
        }
    }
}

// Abstract PaymentMethod class
abstract class PaymentMethod {
    public abstract void pay(double amount);
}

// Concrete classes for different types of payment methods
class CreditCard extends PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPal extends PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class Cash extends PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Cash.");
    }
}

// Abstract factory interface for creating payment methods
interface PaymentFactory {
    PaymentMethod createPaymentMethod();
}

// Concrete factory classes for creating specific payment methods
class CreditCardFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new CreditCard();
    }
}

class PayPalFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new PayPal();
    }
}

class CashFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new Cash();
    }
}

// Main class to combine all the patterns and simulate the ride-sharing application
public class Main {
    public static void main(String[] args) {
        // Singleton pattern: Managing user authentication
        UserAuthentication userAuth = UserAuthentication.getInstance();
        userAuth.login("Manikiran");

        if (userAuth.isAuthenticated()) {
            // Factory Method pattern: Creating a vehicle based on user choice
            VehicleFactory vehicleFactory = new VehicleFactory();
            Vehicle vehicle = vehicleFactory.createVehicle("Car"); // User chooses a Car
            vehicle.ride();

            // Abstract Factory pattern: Creating a payment method based on user choice
            PaymentFactory paymentFactory = new CreditCardFactory(); // User chooses Credit Card
            PaymentMethod paymentMethod = paymentFactory.createPaymentMethod();
            paymentMethod.pay(100.00); // Pay $100 for the ride
        }

        // User logs out
        userAuth.logout();
    }
}
