import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;

    public UserInterface(Scanner scanner) throws IOException {
        this.scanner = scanner;
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
    }

    public void startUI() {

        System.out.println("Choose the option:" +
                "\n 1. List all books and their authors" +
                "\n 2. Add author" +
                "\n 3. Add book" +
                "\n 4. Delete book" +
                "\n Leave blank to exit.");

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("1")) {
                listAllBooks();
            } else if (command.equals("2")) {
                addAuthor();
            } else if (command.equals("3")) {
                addBook();
            } else if (command.equals("4")) {
                deleteBook();
            }
        }
    }

    private void deleteBook() {
        System.out.println("Please provide id of the book to delete:");
        try {
            boolean deleted = bookDAO.deleteBookById(scanner.nextInt());
            if (deleted == true) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No rows were affected, check if the ID is correct and try again.");
            }
        } catch (SQLException e) {
            System.err.println("Error executing the query.");
            e.printStackTrace();
        }
    }

    private void addBook() {
        System.out.println("Please provide name of the book: ");
        String bookName = scanner.nextLine();
        System.out.println("Please provide the year of publishing: ");
        int yearWritten = scanner.nextInt();
        System.out.println("Please provide author's ID: ");
        int authorId = scanner.nextInt();
        Book book = new Book(bookName, yearWritten, null);

        try {
            bookDAO.addBook(book, authorId);
        } catch (SQLException e) {
            System.err.println("Error executing the query");
            e.printStackTrace();
        }

    }

    private void addAuthor() {
        System.out.println("Please provide name of the author to add:");
        String name = scanner.nextLine();
        Author author = new Author(name);

        try {
            authorDAO.insertAuthor(author);
        } catch (SQLException e) {
            System.err.println("Error executing the query");
            e.printStackTrace();
        }

    }

    private Author getAuthor(int id) {
        try {
            return authorDAO.getAuthorByID(id);
        } catch (SQLException e) {
            System.err.println("Error executing the query");
            e.printStackTrace();
        }
        return null;
    }

    private void listAllBooks() {
        List<Book> bookList = null;
        try {
            bookList = bookDAO.getAllBooks();
        } catch (SQLException e) {
            System.err.println("Error executing the query");
            e.printStackTrace();
        }
        for (Book book : bookList) {
            System.out.println("Book name: " + book.getName()
                    + "\nYear written: " + book.getYearWritten()
                    + "\nAuthor: " + book.getAuthor().getName());
            System.out.println();
        }
    }
}
