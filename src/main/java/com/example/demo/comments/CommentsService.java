package com.example.demo.comments;

import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, BookRepository bookRepository) {
        this.commentsRepository = commentsRepository;
        this.bookRepository = bookRepository;
    }

    /*public void saveCommentForBook(Long id, String commentText) throws ChangeSetPersister.NotFoundException {
        // Find the book by its ID
        Book book = bookRepository.findByBookId(id);

        // Create a new comment and set its properties
        Comments comments = new Comments();
        comments.setBook(book);
        comments.setComment(commentText);

        // Save the comment
        commentsRepository.save(comments);
    }
*/
    public List<Comments> getCommentsByBookId(Long id) {
        return commentsRepository.findByBookId(id);
    }


    public List<Comments> getComments() {
        return commentsRepository.findAll(); // Fetch all comments from the repository
    }


    public void addComment(UserEntity userToAdd, Book bookToAdd, String comment) {
        Comments newComment = new Comments();
        newComment.setUser(userToAdd);
        newComment.setBook(bookToAdd);
        newComment.setComment(comment);
        commentsRepository.save(newComment);
    }
}
