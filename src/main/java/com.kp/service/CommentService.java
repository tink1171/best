package com.kp.service;

import com.kp.model.comment.Comment;

import java.util.List;

/**
 * Created by user on 8/21/16.
 */
public interface CommentService {
    List<Comment> findAll();

}
