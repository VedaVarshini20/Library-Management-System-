import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    static class Book {
        private int id;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public int getId() {
            return id;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void setIssued(boolean issued) {
            this.isIssued = issued;
        }

        public void displayInfo() {
            System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued);
        }
    }

    static class User {
        private int userId;
        private String name;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public int getUserId() {
            return userId;
        }

        public void displayUser() {
            System.out.println("User ID: " + userId + ", Name: " + name);
        }
    }

    static class Library {
        private ArrayList<Book> books = new ArrayList<>();
        private ArrayList<User> users = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
        }

        public void addUser(User user) {
            users.add(user);
        }

        public void displayBooks() {
            if (books.isEmpty()) {
                System.out.println("No books available.");
                return;
            }
            for (Book book : books) {
                book.displayInfo();
            }
        }

        public void displayUsers() {
            if (users.isEmpty()) {
                System.out.println("No users registered.");
                return;
            }
            for (User user : users) {
                user.displayUser();
            }
        }

        public void issueBook(int bookId, int userId) {
            Book book = findBookById(bookId);
            User user = findUserById(userId);
            if (book == null) {
                System.out.println("Book not found.");
                return;
            }
            if (user == null) {
                System.out.println("User not found.");
                return;
            }
            if (!book.isIssued()) {
                book.setIssued(true);
                System.out.println("Book issued to User ID: " + userId);
            } else {
                System.out.println("Book is already issued.");
            }
        }

        public void returnBook(int bookId) {
            Book book = findBookById(bookId);
            if (book != null && book.isIssued()) {
                book.setIssued(false);
                System.out.println("Book returned.");
            } else {
                System.out.println("Book not found or not issued.");
            }
        }

        private Book findBookById(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
            return null;
        }

        private User findUserById(int id) {
            for (User user : users) {
                if (user.getUserId() == id) {
                    return user;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Show Books");
            System.out.println("2. Show Users");
            System.out.println("3. Add Book");
            System.out.println("4. Add User");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully.");
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    library.addUser(new User(userId, name));
                    System.out.println("User added successfully.");
                    break;
                case 5:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = scanner.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = scanner.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
