public class Book {


    private String name;
    private int yearWritten;
    private Author author;

    public Book(String name, int yearWritten, Author author) {
        this.name = name;
        this.yearWritten = yearWritten;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearWritten() {
        return yearWritten;
    }

    public void setYearWritten(int yearWritten) {
        this.yearWritten = yearWritten;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return name + ", " + author.getName() + "(" + yearWritten + ")";
    }
}
