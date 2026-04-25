# Ticket AI System

Simple Spring Boot project.

The system takes user comments and automatically creates support tickets if needed.

## What it can do

- Add comments
- Create tickets from comments automatically
- Detect category (bug, account, billing, etc.)
- Detect priority (low, medium, high)
- Try to use AI (Hugging Face)
- If AI doesn't work → uses simple logic
- Basic CRUD for tickets

## Technologies

- Java
- Spring Boot
- JPA (Hibernate)
- H2 Database
- REST API

## Endpoints

### Comments

POST /comments  
Create a new comment

Example:
```json
{
  "text": "I cannot access my account"
}