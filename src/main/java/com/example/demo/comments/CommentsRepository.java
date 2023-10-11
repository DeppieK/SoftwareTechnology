package com.example.demo.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


import javax.xml.stream.events.Comment;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByBookId(Long id);

}
