package com.kp.service;

import com.kp.model.comment.Comment;
import com.kp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 8/21/16.
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    public List<Comment> findAll() {
        return repository.findAll(); }
}
