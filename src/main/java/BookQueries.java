public interface BookQueries {

    String ADD_BOOK_QUERY = "INSERT INTO books (name, year_written, id_author) VALUES (?, ?, ?);";
    String DELETE_BOOK_BY_ID_QUERY = "DELETE FROM books WHERE id = ?";
    String LIST_ALL_BOOKS_QUERY = "SELECT b.name, year_written, a.name\n" +
            "FROM books b\n" +
            "INNER JOIN authors a\n" +
            "ON b.id_author = a.id;";
}
