package com.aleksandr.ticketaisystem.service;

import com.aleksandr.ticketaisystem.model.Ticket;
import org.springframework.stereotype.Service;

@Service // main ticket logic
public class TicketService {

    private final HuggingFaceService huggingFaceService;

    public TicketService(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    // check comment
    public boolean shouldCreateTicket(String text) {
        String lowerText = text.toLowerCase();

        return lowerText.contains("error")
                || lowerText.contains("bug")
                || lowerText.contains("problem")
                || lowerText.contains("not working")
                || lowerText.contains("payment")
                || lowerText.contains("login")
                || lowerText.contains("account")
                || lowerText.contains("urgent")
                || lowerText.contains("cannot use");
    }

    // create ticket from comment text
    public Ticket generateTicket(String text) {
        String aiResponse = huggingFaceService.analyzeText(text);

        Ticket ticket = new Ticket();

        ticket.setTitle(generateTitle(text));
        ticket.setCategory(detectCategory(text));
        ticket.setPriority(detectPriority(text));
        ticket.setSummary(text);

        System.out.println("AI RESPONSE: " + aiResponse);

        return ticket;
    }

    // detect ticket category
    private String detectCategory(String text) {
        String lowerText = text.toLowerCase();

        if (lowerText.contains("payment") || lowerText.contains("billing")) {
            return "billing";
        }

        if (lowerText.contains("login") || lowerText.contains("account")) {
            return "account";
        }

        if (lowerText.contains("feature") || lowerText.contains("add")) {
            return "feature";
        }

        if (lowerText.contains("bug") || lowerText.contains("error") || lowerText.contains("not working")) {
            return "bug";
        }

        return "other";
    }

    // detect ticket priority
    private String detectPriority(String text) {
        String lowerText = text.toLowerCase();

        if (lowerText.contains("urgent") || lowerText.contains("critical") || lowerText.contains("cannot use")) {
            return "high";
        }

        if (lowerText.contains("problem") || lowerText.contains("not working")) {
            return "medium";
        }

        return "low";
    }

    // generate simple ticket title
    private String generateTitle(String text) {
        String lowerText = text.toLowerCase();

        if (lowerText.contains("login") || lowerText.contains("account")) {
            return "Account login issue";
        }

        if (lowerText.contains("payment") || lowerText.contains("billing")) {
            return "Billing issue";
        }

        if (lowerText.contains("bug") || lowerText.contains("error") || lowerText.contains("not working")) {
            return "Application bug report";
        }

        if (lowerText.contains("feature") || lowerText.contains("add")) {
            return "Feature request";
        }

        return "User support request";
    }
}