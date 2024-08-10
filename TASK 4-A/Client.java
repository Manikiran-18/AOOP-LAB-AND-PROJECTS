import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// LogLevel Enum to represent severity levels
enum LogLevel {
    INFO, DEBUG, ERROR
}

// Command interface for logging requests
interface Command {
    void execute(String message);
}

// LogCommand class that executes logging requests using handlers
class LogCommand implements Command {
    private LogHandler handler;

    public LogCommand(LogHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String message) {
        handler.handleMessage(message);
    }
}

// Abstract class for log handlers
abstract class LogHandler {
    protected LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Handle the message and decide if it should be passed to the next handler
    public abstract void handleMessage(String message);
}

// Concrete handler for INFO level logs
class InfoHandler extends LogHandler {
    @Override
    public void handleMessage(String message) {
        if (message.contains("INFO")) {
            System.out.println("INFO: " + message);
        } else if (nextHandler != null) {
            nextHandler.handleMessage(message);
        }
    }
}

// Concrete handler for DEBUG level logs
class DebugHandler extends LogHandler {
    @Override
    public void handleMessage(String message) {
        if (message.contains("DEBUG")) {
            System.out.println("DEBUG: " + message);
        } else if (nextHandler != null) {
            nextHandler.handleMessage(message);
        }
    }
}

// Concrete handler for ERROR level logs
class ErrorHandler extends LogHandler {
    @Override
    public void handleMessage(String message) {
        if (message.contains("ERROR")) {
            System.out.println("ERROR: " + message);
        } else if (nextHandler != null) {
            nextHandler.handleMessage(message);
        }
    }
}

// Logger class that uses an iterator to process a list of commands
class Logger {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void processCommands(String message) {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute(message);
        }
    }
}

// Client class to configure and demonstrate the logging system
public class Client {
    public static void main(String[] args) {
        // Create handlers
        LogHandler infoHandler = new InfoHandler();
        LogHandler debugHandler = new DebugHandler();
        LogHandler errorHandler = new ErrorHandler();

        // Chain handlers
        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        // Create commands
        Command infoCommand = new LogCommand(infoHandler);
        Command debugCommand = new LogCommand(debugHandler);
        Command errorCommand = new LogCommand(errorHandler);

        // Create Logger and add commands
        Logger logger = new Logger();
        logger.addCommand(infoCommand);
        logger.addCommand(debugCommand);
        logger.addCommand(errorCommand);

        // Log messages
        System.out.println("Logging INFO message:");
        logger.processCommands("INFO: This is an info message");

        System.out.println("\nLogging DEBUG message:");
        logger.processCommands("DEBUG: This is a debug message");

        System.out.println("\nLogging ERROR message:");
        logger.processCommands("ERROR: This is an error message");

        System.out.println("\nLogging mixed message:");
        logger.processCommands("ERROR: This is an error message with INFO and DEBUG");
    }
}
