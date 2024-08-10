// Singleton class to manage user login state
class UserSession {
    // Private static variable that stores the single instance of UserSession
    private static UserSession instance;
    
    // Private variable to store the current logged-in user
    private String loggedInUser;

    // Private constructor to prevent instantiation from other classes
    private UserSession() {
        loggedInUser = null;
    }

    // Public static method to provide access to the single instance of UserSession
    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }

    // Method to log in the user
    public void login(String username) {
        if (loggedInUser == null) {
            loggedInUser = username;
            System.out.println(username + " logged in successfully.");
        } else {
            System.out.println("Already logged in as " + loggedInUser + ". Please log out first.");
        }
    }

    // Method to log out the user
    public void logout() {
        if (loggedInUser != null) {
            System.out.println(loggedInUser + " logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Method to check if a user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Method to get the current logged-in user
    public String getLoggedInUser() {
        return loggedInUser;
    }
}

// Banking operations class
class BankingOperations {
    private UserSession userSession;

    public BankingOperations() {
        userSession = UserSession.getInstance();
    }

    public void viewBalance() {
        if (userSession.isLoggedIn()) {
            System.out.println("Displaying balance for " + userSession.getLoggedInUser());
        } else {
            System.out.println("Please log in to view balance.");
        }
    }

    public void deposit(double amount) {
        if (userSession.isLoggedIn()) {
            System.out.println("Depositing " + amount + " for " + userSession.getLoggedInUser());
        } else {
            System.out.println("Please log in to deposit.");
        }
    }

    public void withdraw(double amount) {
        if (userSession.isLoggedIn()) {
            System.out.println("Withdrawing " + amount + " for " + userSession.getLoggedInUser());
        } else {
            System.out.println("Please log in to withdraw.");
        }
    }
}

// Example usage of the banking process with Singleton pattern
public class Main {
    public static void main(String[] args) {
        // Create instances of banking operations
        BankingOperations operations = new BankingOperations();

        // Attempt to perform operations without logging in
        operations.viewBalance();
        operations.deposit(1000);
        operations.withdraw(500);

        // Log in the user
        UserSession userSession = UserSession.getInstance();
        userSession.login("Manikiran");

        // Perform operations after logging in
        operations.viewBalance();
        operations.deposit(1000);
        operations.withdraw(500);

        // Attempt to log in another user
        userSession.login("AnotherUser");

        // Log out the user
        userSession.logout();

        // Attempt to perform operations after logging out
        operations.viewBalance();
    }
}
