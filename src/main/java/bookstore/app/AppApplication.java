package bookstore.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import bookstore.app.domain.AppUser;
import bookstore.app.domain.AppUserRepository;
import bookstore.app.domain.Book;
import bookstore.app.domain.BookRepository;

import bookstore.app.domain.CategoryRepository;
import bookstore.app.domain.Category;

@SpringBootApplication
public class AppApplication {
	
	private static final Logger log = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
 
	@Bean
	public CommandLineRunner BookDemo(BookRepository repository, CategoryRepository drepository, AppUserRepository uRepository) {
		return (args) -> {

			Category category1 = new Category("SCIFI");
			Category category2 = new Category("Biography");
			Category category3 = new Category("Fiction");
			
			drepository.save(category1);
			drepository.save(category2);
			drepository.save(category3);

			log.info("save a couple of books");
			repository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925, 10, category1));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", "9780060935467", 1960, 12, category2));
			repository.save(new Book("1984", "George Orwell", "9780451524935", 1949, 15, category3));
			repository.save(new Book("Moby Dick", "Herman Melville", "9781503280786", 1851, 11, category1));
			repository.save(new Book("Pride and Prejudice", "Jane Austen", "9781503290563", 1813, 9, category1));
			repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", 1951, 13, category3));
			repository.save(new Book("Brave New World", "Aldous Huxley", "9780060850524", 1932, 14, category1));
			repository.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780544003415", 1954, 25, category2));
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", 1937, 16, category1));

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// Hashataan salasanat
			String hashedPassword1 = passwordEncoder.encode("asd");
			String hashedPassword2 = passwordEncoder.encode("asd");

			// Luodaan käyttäjät hashatuilla salasanoilla
			AppUser user1 = new AppUser("user", hashedPassword1, "email@email.com", "USER");
			AppUser user2 = new AppUser("admin", hashedPassword2, "test@email.com", "ADMIN");

			// Tallennetaan käyttäjät tietokantaan
			uRepository.save(user1);
			uRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
}
