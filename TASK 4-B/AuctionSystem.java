import java.util.ArrayList;
import java.util.List;

// Observer interface for receiving notifications
interface Observer {
    void update(String event);
}

// Subject interface for managing observers
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String event);
}

// ConcreteSubject class that manages auction events and notifications
class Auction implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String item;
    private boolean isBiddingOpen;

    // Add an observer to the list
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer from the list
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers of an event
    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    // Set a new item and notify observers
    public void setItem(String item) {
        this.item = item;
        notifyObservers("New item available for auction: " + item);
    }

    // Start bidding and notify observers
    public void startBidding() {
        isBiddingOpen = true;
        notifyObservers("Bidding has started for item: " + item);
    }

    // End bidding and notify observers
    public void endBidding() {
        isBiddingOpen = false;
        notifyObservers("Bidding has ended for item: " + item);
    }
}

// ConcreteObserver class for bidders who want to receive notifications
class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println(name + " received notification: " + event);
    }
}

// Client class to demonstrate the auction system
public class AuctionSystem {
    public static void main(String[] args) {
        // Create an auction
        Auction auction = new Auction();

        // Create bidders
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");

        // Register bidders to the auction
        auction.addObserver(bidder1);
        auction.addObserver(bidder2);

        // Notify about a new item
        auction.setItem("Vintage Painting");

        // Start bidding
        auction.startBidding();

        // End bidding
        auction.endBidding();

        // Unsubscribe a bidder
        auction.removeObserver(bidder1);

        // Notify about another new item
        auction.setItem("Antique Vase");

        // Start bidding for the new item
        auction.startBidding();
    }
}
