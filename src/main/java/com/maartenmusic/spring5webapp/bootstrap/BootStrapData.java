package com.maartenmusic.spring5webapp.bootstrap;

import com.maartenmusic.spring5webapp.domain.Author;
import com.maartenmusic.spring5webapp.domain.Book;
import com.maartenmusic.spring5webapp.domain.Publisher;
import com.maartenmusic.spring5webapp.repositories.AuthorRepository;
import com.maartenmusic.spring5webapp.repositories.BookRepository;
import com.maartenmusic.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher busyBee = new Publisher("Busy Bee", "Utrecht",
                "3434", "Domplein", "1");

        publisherRepository.save(busyBee);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12342341");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(busyBee);
        busyBee.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(busyBee);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "197865432");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(busyBee);
        busyBee.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(busyBee);




        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " +  publisherRepository.count());
        System.out.println("Number of books in publisher roster: " + busyBee.getBooks().size());

        System.out.println(publisherRepository.findById(busyBee.getId()));

    }
}
