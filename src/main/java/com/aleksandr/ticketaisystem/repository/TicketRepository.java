package com.aleksandr.ticketaisystem.repository;

import com.aleksandr.ticketaisystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}