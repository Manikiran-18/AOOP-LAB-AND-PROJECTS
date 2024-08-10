import java.util.HashMap;
import java.util.Map;

// 1. Service Interfaces
interface PaymentService {
    void processPayment(double amount);
}

interface NotificationService {
    void sendNotification(String message);
}

// 2. Concrete Implementations
class CreditCardPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}

// 3. Service Locator
class ServiceLocator {
    private static final Map<Class<?>, Object> services = new HashMap<>();

    // Register a service
    public static <T> void registerService(Class<T> serviceClass, T serviceInstance) {
        services.put(serviceClass, serviceInstance);
    }

    // Retrieve a service
    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> serviceClass) {
        return (T) services.get(serviceClass);
    }
}

// 4. Client Application
public class Application {
    public static void main(String[] args) {
        // Register services
        ServiceLocator.registerService(PaymentService.class, new CreditCardPaymentService());
        ServiceLocator.registerService(NotificationService.class, new EmailNotificationService());

        // Retrieve and use services
        PaymentService paymentService = ServiceLocator.getService(PaymentService.class);
        NotificationService notificationService = ServiceLocator.getService(NotificationService.class);

        paymentService.processPayment(150.00);
        notificationService.sendNotification("Your payment was successful.");
    }
}
