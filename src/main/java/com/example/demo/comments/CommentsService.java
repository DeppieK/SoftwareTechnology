package com.example.demo.comments;

import com.example.demo.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
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

    public void saveComment(Comments comments) {
        commentsRepository.save(comments); // Now it should work with your custom Comment entity
    }

    public List<Comments> getCommentsByBookId(Long bookId) {
        return commentsRepository.findByBookId(bookId);
    }


    public List<Comments> getComments() {
        return commentsRepository.findAll(); // Fetch all comments from the repository
    }




}
