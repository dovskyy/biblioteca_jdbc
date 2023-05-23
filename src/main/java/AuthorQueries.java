public interface AuthorQueries {
    String INSERT_AUTHOR_QUERY = "INSERT INTO authors (name) VALUES (?);";
    String GET_AUTHOR_BY_ID = "SELECT id, name FROM authors WHERE id = ?;";

}
