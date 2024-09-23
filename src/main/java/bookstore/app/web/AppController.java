package bookstore.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookstore.app.domain.Book;
import bookstore.app.domain.BookRepository;
import bookstore.app.domain.CategoryRepository;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
	private BookRepository repository; 

    @Autowired
	private CategoryRepository drepository; 

    @RequestMapping(value= {"/", "/books"})
    public String books(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "books";
    }

    // RESTful service to get all students
    @RequestMapping(value="/restbooks", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }
    
    // RESTful service to get student by id
    @RequestMapping(value="/restbooks/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long Id) {	
    	return repository.findById(Id);
    } 

    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        model.addAttribute("category", drepository.findAll());
        return "add";
    }   

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        Book book = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("category", drepository.findAll());
        return "edit";
    }    
    
    @PostMapping("/save")
    public String saveBook(Book book) {
        
        logger.info("Saving book: id={}, title={}, author={}, isbn={}, year={}, price={}",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(), book.getBookprice());
        
        if (book.getId() > 0 && repository.existsById(book.getId())) {
            repository.save(book);
            logger.info("Book updated successfully.");
        }
        else {
            repository.save(book);
            logger.info("New book saved successfully.");
        }
        return "redirect:/books";
    }   

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long Id, Model model) {
    	repository.deleteById(Id);
        return "redirect:../books";
    } 
}
