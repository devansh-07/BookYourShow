# BookYourShow

**BookYourShow** is a ticket booking platform for events like movies, concerts, and theater performances, built using a **microservices architecture** for scalability and flexibility.

## Features

- **User Management**: Register, log in, and manage profiles.  
- **Event Management**: Browse and filter events.  
- **Ticket Booking**: Select seats and book tickets.  
- **Payments**: Secure payment processing.  
- **Notifications**: Email/SMS confirmations and reminders.

## Microservices

1. **User Service**: Manages user data and authentication.  
2. **Event Service**: Handles event details.  
3. **Ticket Service**: Manages bookings and seat inventory.  
4. **Payment Service**: Processes payments securely.  
5. **Notification Service**: Sends notifications.

### Additional Components

- **API Gateway**: Routes requests using **Spring Cloud Gateway**.  
- **Discovery Server**: Service registry with **Netflix Eureka**.

## Tech Stack

- **Backend**: Spring Boot  
- **Database**: PostgreSQL  
- **Messaging**: RabbitMQ/Kafka  
- **Service Discovery**: Netflix Eureka  

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/devansh-07/BookYourShow.git
   ```

2. Build and run each service:
   ```bash
   cd ServiceName
   mvn clean install
   mvn spring-boot:run
   ```