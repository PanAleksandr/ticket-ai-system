package com.aleksandr.ticketaisystem.controller;

import com.aleksandr.ticketaisystem.model.Comment;
import com.aleksandr.ticketaisystem.model.Ticket;
import com.aleksandr.ticketaisystem.repository.CommentRepository;
import com.aleksandr.ticketaisystem.repository.TicketRepository;
import com.aleksandr.ticketaisystem.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public CommentController(CommentRepository commentRepository,
                             TicketRepository ticketRepository,
                             TicketService ticketService) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    // Get all comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Create com and generat ticket
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        Comment savedComment = commentRepository.save(comment);

        // check
        if (ticketService.shouldCreateTicket(comment.getText())) {

            // generate ticket
            Ticket ticket = ticketService.generateTicket(comment.getText());

            // link ticket to comment
            ticket.setCommentId(savedComment.getId());

            ticketRepository.save(ticket);
        }

        return savedComment;
    }
}