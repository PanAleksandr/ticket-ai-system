package com.aleksandr.ticketaisystem.controller;

import com.aleksandr.ticketaisystem.model.Ticket;
import com.aleksandr.ticketaisystem.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Get alll tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get ticket by id
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        if (!ticketRepository.existsById(id)) {
            return "Ticket not found";
        }

        ticketRepository.deleteById(id);
        return "Ticket deleted";
    }

    // Update
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setTitle(updatedTicket.getTitle());
        ticket.setCategory(updatedTicket.getCategory());
        ticket.setPriority(updatedTicket.getPriority());
        ticket.setSummary(updatedTicket.getSummary());

        return ticketRepository.save(ticket);
    }
}