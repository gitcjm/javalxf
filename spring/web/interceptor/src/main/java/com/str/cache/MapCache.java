package com.str.cache;

import com.str.entity.Book;
import com.str.service.EntityService;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * MapCache泛化后，就不需要定义具体类型的缓存了，如：BookMapCache, UserMapCache ...
 *
 * 改造成泛型缓存后，就无法使map静态化了。所以，还是要单例！
 * 标注为@Component的类, spring都会生成单例。不是的！不是单例
 * 还得自己实现单例
 * */
//@Component
public class MapCache<E> extends AbstractCache<E> {

    private Map<Long, E> cache = new HashMap<>();

    private EntityService entityService;

    private static MapCache INSTANCE = null;

    // 据廖雪峰说，这种实现方式在多线程中是错误的，在竞争条件下会创建出多个实例。（见廖雪峰的java教程，单例设计模式）
    // 先这样吧！以后使用枚举类型实现单例模式。
    public static MapCache getINSTANCE(EntityService entityService) {
        if (INSTANCE == null) {
            INSTANCE = new MapCache(entityService);
        }
        return INSTANCE;
    }

    private MapCache(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    protected E lookupCache(long id) {
        return cache.get(id);
    }

    @Override
    protected void putIntoCache(long id, E e) {
        cache.put(id, e);
    }

    @Override
    protected E readFromDB(long id) {
        return (E) entityService.getEntityById(id);
    }
}

/*@Component
public class BookMapCache extends AbstractCache<Book> {
    // 只需单例缓存对象
    private static final Map<Long, Book> cache = new HashMap<>();

    private BookService bookService;

    public BookMapCache(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected Book lookupCache(long id) {
        return cache.get(id);
    }

    @Override
    protected void putIntoCache(long id, Book book) {
        cache.put(id, book);
    }

    @Override
    protected Book readFromDB(long id) {
        return bookService.getEntityById(id);
    }
}*/

/**
 * spring把标注@Component的类都只生成单例对象, 不确定我的类是不是产生单例！
 * 所以，我还是以单例模式设计此类
 */

/*@Component
public class BookMapCache extends AbstractCache<Book> {
    //@Autowired // spring单例的问题，所以此处不能再次注入
    private BookService bookService;

    private Map<Long, Book> cache = new HashMap<>();

    private static BookMapCache INSTANCE = null;

    public static BookMapCache getInstance(BookService bookService) {
        if (INSTANCE == null) {
            INSTANCE = new BookMapCache(bookService);
        }
        return INSTANCE;
    }

    private BookMapCache(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected Book lookupCache(long id) {
        return cache.get(id);
    }

    @Override
    protected void putIntoCache(long id, Book book) {
        cache.put(id, book);
    }

    @Override
    protected Book readFromDB(long id) {
        return bookService.getBookById(id);
    }
}*/
