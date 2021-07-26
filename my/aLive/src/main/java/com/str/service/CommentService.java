package com.str.service;

import com.str.entity.Book;
import com.str.entity.Comment;
import com.str.entity.User;
import com.str.util.Page;

import java.util.List;

public class CommentService extends AbstractService<Comment> {

    // 一本书的评论分页显示
    public List<Comment> queryComments(Book book, Page page) {
        return queryForListHasPages(
                "select count(*) from Comment c where c.book=?0",
                "select c from Comment c where c.book=?0 order by c.createdAt desc",
                new Object[] { book },
                page);
    }


}
