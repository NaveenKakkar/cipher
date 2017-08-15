package duster.ramson;

import duster.ramson.dao.CustomerRepository;
import duster.ramson.entity.BookMany;
import duster.ramson.entity.BookP;
import duster.ramson.entity.BookCategory;
import duster.ramson.entity.BookDetail;
import duster.ramson.entity.Customer;
import duster.ramson.entity.Publisher;
import duster.ramson.jpa.repository.BookCategoryRepository;
import duster.ramson.jpa.repository.BookRepository;
import duster.ramson.jpa.repository.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PublisherRepository publisherRepository;
    
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private BookRepository bookRepository;


    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        // save a couple of books
        Publisher publisherA = new Publisher("Publisher A");
        Publisher publisherB = new Publisher("Publisher B");
        Publisher publisherC = new Publisher("Publisher C");

        bookRepository.save(new HashSet<BookP>(){{
            add(new BookP("Book A", new HashSet<Publisher>(){{
                add(publisherA);
                add(publisherB);
            }}));

            add(new BookP("Book B", new HashSet<Publisher>(){{
                add(publisherA);
                add(publisherC);
            }}));
        }});

        // fetch all books
        for(BookP book : bookRepository.findAll()) {
            logger.info(book.toString());
        }

        // save a couple of publishers
        BookP bookA = new BookP("Book A");
        BookP bookB = new BookP("Book B");

        publisherRepository.save(new HashSet<Publisher>() {{
            add(new Publisher("Publisher A", new HashSet<BookP>() {{
                add(bookA);
                add(bookB);
            }}));

            add(new Publisher("Publisher B", new HashSet<BookP>() {{
                add(bookA);
                add(bookB);
            }}));
        }});

        // fetch all publishers
        for(Publisher publisher : publisherRepository.findAll()) {
            logger.info(publisher.toString());
        }
    }    
    
//    @Override
//    @Transactional
//    public void run(String... strings) throws Exception {
//        // save a couple of categories
//        BookCategory categoryA = new BookCategory("Category A");
//        Set bookAs = new HashSet<BookMany>(){{
//            add(new BookMany("Book A1", categoryA));
//            add(new BookMany("Book A2", categoryA));
//            add(new BookMany("Book A3", categoryA));
//        }};
//        categoryA.setBooks(bookAs);
//
//        BookCategory categoryB = new BookCategory("Category B");
//        Set bookBs = new HashSet<BookMany>(){{
//            add(new BookMany("Book B1", categoryB));
//            add(new BookMany("Book B2", categoryB));
//            add(new BookMany("Book B3", categoryB));
//        }};
//        categoryB.setBooks(bookBs);
//
//        bookCategoryRepository.save(new HashSet<BookCategory>() {{
//            add(categoryA);
//            add(categoryB);
//        }});
//
//        // fetch all categories
//        for (BookCategory bookCategory : bookCategoryRepository.findAll()) {
//            logger.info(bookCategory.toString());
//        }
//    }
//    
//    @Override
//    public void run(String... strings) throws Exception {
//        // save a couple of books
//        List<Book> books = new ArrayList<>();
//        books.add(new Book("Book A", new BookDetail(49)));
//        books.add(new Book("Book B", new BookDetail(59)));
//        books.add(new Book("Book C", new BookDetail(69)));
//        bookRepository.save(books);
//
//        // fetch all books
//        for (Book book : bookRepository.findAll()) {
//            logger.info(book.toString());
//        }
//    }
    
//    
//    @Transactional(readOnly = true)
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("DATASOURCE = " + dataSource);
//
//        System.out.println("\n1.findAll()...");
//        for (Customer customer : customerRepository.findAll()) {
//            System.out.println(customer);
//        }
//
//        System.out.println("\n2.findByEmail(String email)...");
//        for (Customer customer : customerRepository.findByEmail("222@yahoo.com")) {
//            System.out.println(customer);
//        }
//
//        System.out.println("\n3.findByDate(Date date)...");
//        for (Customer customer : customerRepository.findByDate(sdf.parse("2017-02-12"))) {
//            System.out.println(customer);
//        }
//
//        // For Stream, need @Transactional
//        System.out.println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
//        try (Stream<Customer> stream = customerRepository.findByEmailReturnStream("333@yahoo.com")) {
//            stream.forEach(x -> System.out.println(x));
//        }
//
//        System.out.println("Done!");
//
//        exit(0);
//    }

}