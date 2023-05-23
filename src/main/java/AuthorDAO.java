import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO {

    private MysqlConnection mysqlConnection;

    public AuthorDAO() throws IOException {
        mysqlConnection = new MysqlConnection();
    }

    public void insertAuthor(Author author) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AuthorQueries.INSERT_AUTHOR_QUERY);
            preparedStatement.setString(1, author.getName());
            preparedStatement.executeUpdate();
        }
    }

    public Author getAuthorByID(int id) throws SQLException {
        try(Connection connection = mysqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AuthorQueries.GET_AUTHOR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String name = resultSet.getString(2);
            Author author = new Author(name);
            return author;
        }
    }
}
