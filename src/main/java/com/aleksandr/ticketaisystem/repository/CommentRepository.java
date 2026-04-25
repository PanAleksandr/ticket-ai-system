package com.aleksandr.ticketaisystem.repository;

import com.aleksandr.ticketaisystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
//repos
public interface CommentRepository extends JpaRepository<Comment, Long> {
}