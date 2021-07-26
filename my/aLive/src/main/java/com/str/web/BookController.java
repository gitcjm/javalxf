package com.str.web;

import com.str.entity.Book;
import com.str.service.BookService;
import com.str.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public ModelAndView bookIndex() {
        return new ModelAndView("book-search.html");
    }

    @GetMapping("/book-list")
    public ModelAndView getBookList() {
        List<Book> books = bookService.getBookList(0, new Page(1));
        return new ModelAndView("book-list.html", Map.of("books", books));
    }

    @GetMapping("/book-add")
    public ModelAndView addBook() {
        return new ModelAndView("book-add.html");
    }

    /*@PostMapping("/book-add")
    public ModelAndView doAddBook(@RequestParam("name") String name,
                                  @RequestParam("originalName") String originalName,
                                  @RequestParam("categoryCode") int categoryCode,
                                  @RequestParam("author") String author,
                                  @RequestParam("publisher") String publisher,
                                  @RequestParam("language") String language,
                                  @RequestParam("price") float price,
                                  @RequestParam("discount") int discount,
                                  @RequestParam("isbn") String isbn,
                                  @RequestParam("pubDate") String pubDate,
                                  @RequestParam("state") int state,
                                  @RequestParam("stock") int stock,
                                  @RequestParam("sold") int sold,
                                  @RequestParam("rating") int rating,
                                  @RequestParam("ratingCount") int ratingCount,
                                  @RequestParam("description") String description) {
        try {
            bookService.newBook(name, originalName, categoryCode, author, publisher, language, price,
                    discount, isbn, pubDate, state, stock, sold, rating, ratingCount, description);
        } catch (RuntimeException e) {
            return new ModelAndView("book-add.html",
                    Map.of("error", "Add book failed." + "\n" + e.getMessage()));
        }
        return new ModelAndView("redirect:/book/book-list");
    }*/

    /**
     * 通过HttpServletRequest获取参数意义重大！
     * 这样就可以通过反射来获取Form提交的参数值
     * */
    @PostMapping("/book-add")
    public ModelAndView doAddBook(HttpServletRequest request) {
        String name = request.getParameter("name");
        String originalName = request.getParameter("originalName");
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String language = request.getParameter("language");
        float price = Float.parseFloat(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        String isbn = request.getParameter("isbn");
        String pubDate = request.getParameter("pubDate");
        int state = Integer.parseInt(request.getParameter("state"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int sold = Integer.parseInt(request.getParameter("sold"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        int ratingCount = Integer.parseInt(request.getParameter("ratingCount"));
        String description = request.getParameter("description");

        try {
            bookService.newBook(name, originalName, categoryCode, author, publisher, language, price,
                    discount, isbn, pubDate, state, stock, sold, rating, ratingCount, description);
        } catch (RuntimeException e) {
            return new ModelAndView("book-add.html",
                    Map.of("error", "Add book failed." + "\n" + e.getMessage()));
        }
        return new ModelAndView("redirect:/book/book-list");
    }

    @GetMapping("/book-detail/{id}")
    public ModelAndView bookDetail(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return new ModelAndView("book-detail.html", Map.of("book", book));
    }

    @GetMapping("/book-update/{id}")
    public ModelAndView updateBook(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return new ModelAndView("book-update.html", Map.of("book", book));
    }

    @PostMapping("/book-update")
    public ModelAndView doUpdateBook(@RequestParam("id") long id,
                                     @RequestParam("name") String name,
                                     @RequestParam("originalName") String originalName,
                                     @RequestParam("categoryCode") int categoryCode,
                                     @RequestParam("author") String author,
                                     @RequestParam("publisher") String publisher,
                                     @RequestParam("language") String language,
                                     @RequestParam("price") float price,
                                     @RequestParam("discount") int discount,
                                     @RequestParam("isbn") String isbn,
                                     @RequestParam("pubDate") String pubDate,
                                     @RequestParam("state") int state,
                                     @RequestParam("stock") int stock,
                                     @RequestParam("sold") int sold,
                                     @RequestParam("rating") int rating,
                                     @RequestParam("ratingCount") int ratingCount,
                                     @RequestParam("description") String description) {
        try {
            Book book = bookService.getBookById(id);
            bookService.updateBook(book, name, originalName, categoryCode, author, publisher, language,
                    price, discount, isbn, pubDate, state, stock, sold, rating, ratingCount, description);
        } catch (RuntimeException e) {
            return new ModelAndView("book-update.html",
                    Map.of("error", "Update book failed." + "\n" + e.getMessage()));
        }
        return new ModelAndView("redirect:/book/book-list");
    }
}
