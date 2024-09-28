package bookstore.app.domain;

import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private float bookprice;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    public Book() {
    }

    public Book(String title, String author, String isbn, int publicationYear, float bookprice, Category category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.bookprice = bookprice;
        this.category = category;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

     @PreAuthorize("hasAuthority('ADMIN')")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public float getBookprice() {
        return bookprice;
    }

    public void setBookprice(float bookprice) {
        this.bookprice = bookprice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", bookprice=" + bookprice +
                ", category=" + (category != null ? category.getCategory() : "None") +
                '}';
    }
}
