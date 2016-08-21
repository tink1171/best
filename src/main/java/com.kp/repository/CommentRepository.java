package com.kp.repository;

import com.kp.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 8/21/16.
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
