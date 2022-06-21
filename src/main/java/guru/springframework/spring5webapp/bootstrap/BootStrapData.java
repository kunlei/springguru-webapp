package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Started in Bootstrap");
    Publisher publisher = new Publisher();
    publisher.setName("SFG publishing");
    publisher.setCity("st Petersburg");
    publisher.setState("FL");
    publisherRepository.save(publisher);

    System.out.println("Publisher count: " + publisherRepository.count());

    Author eric = new Author("Eric", "Evans");
    Book book = new Book("Domain Driven Design", "123123");
    eric.getBooks().add(book);
    book.getAuthors().add(eric);
    book.setPublisher(publisher);
    publisher.getBooks().add(book);

    authorRepository.save(eric);
    bookRepository.save(book);

    Author rod = new Author("Rod", "Johnson");
    Book noEjb = new Book("J2ee dev without ejb", "122323");
    rod.getBooks().add(noEjb);
    noEjb.getAuthors().add(rod);
    noEjb.setPublisher(publisher);
    publisher.getBooks().add(noEjb);

    authorRepository.save(rod);
    bookRepository.save(noEjb);

    System.out.println("Started in Bootstrap");
    System.out.println("Number of books: " + bookRepository.count());
  }
}
