package com.str.web;

import com.str.cache.AbstractCache;
import com.str.cache.MapCache;
import com.str.entity.Book;
import com.str.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    public static final String KEY_BOOK = "__book__";

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ModelAndView bookList() {
        List<Book> books = bookService.getEntityList(1, 5);
        return new ModelAndView("books.html", "books", books);
    }

    @GetMapping("/book-detail/{id}")
    public ModelAndView bookDetail(@PathVariable("id") long id) {
        // 正式运行时，数据是要放在缓存中的
        //Book book = bookService.getBookById(id);
        AbstractCache<Book> cache = MapCache.getINSTANCE(bookService);
        Book book = cache.getEntity(id);
        return new ModelAndView("book-detail.html", Map.of("book", book));
    }

    @GetMapping("/add-book")
    public ModelAndView addBook() {
        return new ModelAndView("add-book.html");
    }

    @PostMapping("/add-book")
    public ModelAndView doAddBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("intro") String intro) {
        try {
            Book book = bookService.addBook(title, author, intro);
        } catch (RuntimeException e) {
            return new ModelAndView("add-book.html",
                    Map.of("title", title, "error", "Add failed."));
        }
        return new ModelAndView("redirect:/book/books");
    }

}
