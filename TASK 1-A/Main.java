// Logger class using Singleton pattern
class Logger {
    // Private static variable that stores the single instance of Logger
    private static Logger instance;
    
    // Private constructor to prevent instantiation from other classes
    private Logger() {
        // Initialize logger resources, if any
    }
    
    // Public static method to provide access to the single instance of Logger
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    // Method to log messages
    public void log(String message) {
        // For simplicity, we're just printing the message to the console
        System.out.println("Log: " + message);
    }
}

// Example usage of Logger in an application
public class Main {
    public static void main(String[] args) {
        // Get the single instance of Logger
        Logger logger = Logger.getInstance();
        
        // Log some messages
        logger.log("Application started");
        logger.log("User logged in");
        logger.log("Error: Unable to connect to database");
        logger.log("Application terminated");
    }
}
