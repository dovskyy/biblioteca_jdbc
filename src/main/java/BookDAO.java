import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private MysqlConnection mysqlConnection;

    public BookDAO() throws IOException {
        mysqlConnection = new MysqlConnection();
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = mysqlConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(BookQueries.LIST_ALL_BOOKS_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int yearWritten = resultSet.getInt(2);
                Author author = new Author(resultSet.getString(3));
                Book book = new Book(name, yearWritten, author);
                bookList.add(book);
            }
        }

        return bookList;

    }

    public boolean addBook(Book book, int authorId) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(BookQueries.ADD_BOOK_QUERY);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getYearWritten());
            preparedStatement.setInt(3, authorId);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean deleteBookById(int id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(BookQueries.DELETE_BOOK_BY_ID_QUERY);
            statement.setInt(1, id);

            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
