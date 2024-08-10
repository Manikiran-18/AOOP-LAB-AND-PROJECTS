import java.util.ArrayList;
import java.util.List;

// Book Class (SRP)
class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

// Member Class (SRP)
class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    // Getters and setters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
}

// Library Interface (ISP)
interface Library {
    void addBook(Book book);
    void removeBook(String ISBN);
    void registerMember(Member member);
    void lendBook(String ISBN, String memberId);
    void returnBook(String ISBN);
}

// NotificationService Interface (ISP)
interface NotificationService {
    void notifyMember(String memberId, String message);
}

// EmailNotificationService Class (DIP)
class EmailNotificationService implements NotificationService {
    @Override
    public void notifyMember(String memberId, String message) {
        // Send an email to the member
        System.out.println("Email sent to " + memberId + ": " + message);
    }
}

// LibraryManagement Class (SRP, OCP)
class LibraryManagement implements Library {
    private List<Book> books;
    private List<Member> members;

    public LibraryManagement() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(String ISBN) {
        books.removeIf(book -> book.getISBN().equals(ISBN));
    }

    @Override
    public void registerMember(Member member) {
        members.add(member);
    }

    @Override
    public void lendBook(String ISBN, String memberId) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book " + book.getTitle() + " lent to " + memberId);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    @Override
    public void returnBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book " + book.getTitle() + " returned.");
                return;
            }
        }
        System.out.println("Book was not lent out.");
    }
}

// LibraryManagementWithNotifications Class (DIP)
class LibraryManagementWithNotifications extends LibraryManagement {
    private NotificationService notificationService;

    public LibraryManagementWithNotifications(NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    @Override
    public void lendBook(String ISBN, String memberId) {
        super.lendBook(ISBN, memberId);
        notificationService.notifyMember(memberId, "You have borrowed the book with ISBN " + ISBN);
    }

    @Override
    public void returnBook(String ISBN) {
        super.returnBook(ISBN);
        notificationService.notifyMember("Admin", "The book with ISBN " + ISBN + " has been returned.");
    }
}

// Main Class (Entry Point)
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new LibraryManagementWithNotifications(new EmailNotificationService());

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "123456");
        Book book2 = new Book("1984", "George Orwell", "654321");
        Member member1 = new Member("M001", "Mani");

        library.addBook(book1);
        library.addBook(book2);
        library.registerMember(member1);

        library.lendBook("123456", "M001");
        library.returnBook("123456");
    }
}
