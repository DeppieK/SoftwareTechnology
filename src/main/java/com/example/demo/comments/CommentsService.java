package com.example.demo.comments;

import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, BookRepository bookRepository) {
        this.commentsRepository = commentsRepository;
        this.bookRepository = bookRepository;
    }


    public List<Comments> getCommentsByBookId(Long id) {
        return commentsRepository.findByBookId(id);
    }


    public List<Comments> getComments() {
        return commentsRepository.findAll();
    }


    public void addComment(UserEntity userToAdd, Book bookToAdd, String comment) {
        Comments newComment = new Comments();
        newComment.setUser(userToAdd);
        newComment.setBook(bookToAdd);
        newComment.setComment(comment);
        commentsRepository.save(newComment);
    }
}
