API Endpoints and Microservice Documentation



1. User Service (Port: 8081)


POST /users/register
Registers a new user.

Request Body:
{
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "securepassword"
}

Response:
{
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com",
    "balance": 0
}
GET /users/login
Logs in a user and generates a token.

Request Params:

json
Copy code
{
    "email": "johndoe@example.com",
    "password": "securepassword"
}
Response:

json
Copy code
{
    "token": "jwt-token-here"
}
POST /users/logout
Logs out the current user by invalidating the token.

GET /users/{id}
Retrieves user profile information.

Path Params:

id: User ID
Response:

json
Copy code
{
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com",
    "balance": 1000
}
PUT /users/balance
Updates the user balance asynchronously.

Request Body:

json
Copy code
{
    "balance": 500
}
Response:

json
Copy code
{
    "message": "Balance updated successfully"
}
2. Train Service (Port: 8082)
GET /trains
Fetches the list of trains based on search parameters.

Request Body:

json
Copy code
{
    "from": "Dhaka",
    "to": "Chittagong",
    "date": "2024-11-01"
}
Response:

[
    {
        "id": 1,
        "name": "Subarna Express",
        "fromPlace": "Dhaka",
        "toPlace": "Chittagong",
        "departureTime": "08:00 AM",
        "noOfSeats": 275
    },
    {
        "id": 2,
        "name": "Turna Nishita",
        "fromPlace": "Dhaka",
        "toPlace": "Chittagong",
        "departureTime": "11:30 PM",
        "noOfSeats": 300
    }
]
GET /trains/{trainId}/seats
Retrieves the seat list for a specific train.

Path Params:

trainId: ID of the train.
Response:

json
Copy code
[
    {
        "id": 1,
        "class": "First Class",
        "fare": 1500,
        "status": "Available"
    },
    {
        "id": 2,
        "class": "Shovon",
        "fare": 800,
        "status": "Booked"
    }
]
3. Booking Service (Port: 8083)
POST /booking
Creates a booking and generates an OTP for seat verification.

Request Body:

json
Copy code
{
    "userId": 1,
    "seatIds": [1, 2]
}
Response:

json
Copy code
{
    "bookingId": 123,
    "otp": 4567
}
POST /booking/verify
Verifies the booking OTP and confirms the booking.

Request Body:

json
Copy code
{
    "userId": 1,
    "bookingId": 123,
    "otp": 4567
}
Response:

json
Copy code
{
    "message": "Booking verified successfully",
    "bookingStatus": "Confirmed"
}
4. Payment Service (Port: 8084)
POST /payment
Processes the payment for a booking and generates the ticket.

Request Body:

json
Copy code
{
    "userId": 1,
    "bookingId": 123,
    "passengerList": ["John Doe", "Jane Doe"]
}
Response:

json
Copy code
{
    "paymentId": 9876,
    "ticketId": 5678
}
GET /payment/ticket
Fetches all tickets for a specific user.

Request Body:

json
Copy code
{
    "userId": 1
}
Response:

json
Copy code
[
    {
        "ticketId": 5678,
        "userId": 1,
        "bookingId": 123,
        "paymentId": 9876
    }
]
GET /payment/ticket/{ticketId}
Fetches a specific ticket based on ticket ID.

Path Params:

ticketId: ID of the ticket.
Response:

json
Copy code
{
    "ticketId": 5678,
    "userId": 1,
    "bookingId": 123,
    "paymentId": 9876
}
DELETE /payment/ticket/{ticketId}
Cancels the ticket, deletes booking and payment information, and updates seat status asynchronously.

Path Params:

ticketId: ID of the ticket.
Response:

json
Copy code
{
    "message": "Ticket cancelled successfully, seats are now available."
}
