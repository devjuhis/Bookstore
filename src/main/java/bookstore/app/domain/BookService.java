package bookstore.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Book save(Book book) {
        return bookRepository.save(book); // Only accessible by ADMIN
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(Long id) {
        bookRepository.deleteById(id); // Only accessible by ADMIN
    }
}
