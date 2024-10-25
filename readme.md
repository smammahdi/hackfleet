Here is the `README.md` file for the User Microservice API documentation:

# User Microservice API

This is the API documentation for the **User Microservice**. This service allows users to register, log in, retrieve user details, and manage user account balances. The API uses JWT-based authentication for secure access to certain endpoints.

## Table of Contents

- [Installation](#installation)
- [API Endpoints](#api-endpoints)
  - [Register a New User](#1-register-a-new-user)
  - [User Login](#2-user-login)
  - [Get User by ID](#3-get-user-by-id)
  - [Add Balance to User's Account](#4-add-balance-to-users-account)
  - [Reduce Balance from User's Account](#5-reduce-balance-from-users-account)
- [Authorization](#authorization)

---

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-repository/user-microservice.git
    ```

2. Navigate to the project directory:

    ```bash
    cd user-microservice
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application should now be running locally on `http://20.115.178.62`.


## API Endpoints

### 1. Register a New User

**Endpoint**: `POST /users/register`

#### Request Body:

```json
{
  "nid": "long",
  "name": "string",
  "email": "string",
  "password": "string",
  "money": "long"
}
```

#### Response:

- **Body**:
  ```json
  {
    "token": "string"
  }
  ```
- **Status Code**: `200 OK`

---

### 2. User Login

**Endpoint**: `POST /users/login`

#### Request Body:

```json
{
  "email": "string",
  "password": "string"
}
```

#### Response:

- **Body**:
  ```json
  {
    "token": "string"
  }
  ```
- **Status Code**: `200 OK`

---

### 3. Get User by ID

**Endpoint**: `GET /users/{id}`

#### Path Variable:

- `id`: User's ID (Long)

#### Response:

- **Body**:
  ```json
  {
    "id": "long",
    "nid": "long",
    "name": "string",
    "email": "string",
    "money": "long"
  }
  ```
- **Status Code**: `200 OK`

---

### 4. Add Balance to User's Account

**Endpoint**: `PUT /users/balance/add`

#### Request Parameters:

- `userId`: User's ID (Long)
- `balance`: Amount to add to the user's balance (Long)

#### Response:

- **Body**: `"Balance updated successfully"`
- **Status Code**: `200 OK`

---

### 5. Reduce Balance from User's Account

**Endpoint**: `PUT /users/balance/reduce`

#### Request Parameters:

- `userId`: User's ID (Long)
- `balance`: Amount to reduce from the user's balance (Long)

#### Response:

- **Body**: `"Balance updated successfully"`
- **Status Code**: `200 OK`

---

## Authorization

For endpoints like **Get User by ID**, **Add Balance**, and **Reduce Balance**, you need to provide the JWT token in the request headers.

#### Header Example:

```bash
Authorization: Bearer <token>
```

Replace `<token>` with the actual token you received upon registration or login.

---


# Trains Microservice API

This documentation provides an overview of the **Trains Microservice**. This service allows users to search for trains, view seats, manage train data, and update seat statuses.

## Table of Contents

- [API Endpoints](#api-endpoints)
  - [Get Trains by From, To, and Date](#1-get-trains-by-from-to-and-date)
  - [Get Seats by Train ID](#2-get-seats-by-train-id)
  - [Add New Trains with Seats](#3-add-new-trains-with-seats)
  - [Get All Trains with Seats](#4-get-all-trains-with-seats)
  - [Set Seat Status and Calculate Total Cost](#5-set-seat-status-and-calculate-total-cost)

---

## API Endpoints

### 1. Get Trains by From, To, and Date

**Endpoint**: `GET /trains`

#### Description:
Retrieve a list of trains based on the departure location, destination, and date.

#### Request Body:

```json
{
  "fromPlace": "string",
  "toPlace": "string",
  "departureDate": "yyyy-MM-dd"
}
```

#### Response:

- **Body**:
  ```json
  [
    {
      "id": "long",
      "name": "string",
      "fromPlace": "string",
      "toPlace": "string",
      "departureTime": "yyyy-MM-ddTHH:mm:ss",
      "seats": [...]
    }
  ]
  ```
- **Status Code**: `200 OK`

---

### 2. Get Seats by Train ID

**Endpoint**: `GET /trains/{trainId}/seats`

#### Description:
Retrieve a list of seats for a specific train. You can optionally filter the seats by class.

#### Path Variable:
- `trainId`: The ID of the train.

#### Request Parameters:
- `seatClass` (optional): The class of the seats (e.g., "Economy", "Business").

#### Response:

- **Body**:
  ```json
  [
    {
      "seatId": "long",
      "seatClass": "string",
      "status": "string",
      "price": "long"
    }
  ]
  ```
- **Status Code**: `200 OK`

---

### 3. Add New Trains with Seats

**Endpoint**: `POST /trains/add`

#### Description:
Add new trains with their associated seats. You can add multiple trains at once.

#### Request Body:

```json
[
  {
    "name": "string",
    "fromPlace": "string",
    "toPlace": "string",
    "departureTime": "yyyy-MM-ddTHH:mm:ss",
    "seats": [
      {
        "seatClass": "string",
        "status": "string",
        "price": "long"
      }
    ]
  }
]
```

#### Response:

- **Body**:
  ```json
  [
    {
      "id": "long",
      "name": "string",
      "fromPlace": "string",
      "toPlace": "string",
      "departureTime": "yyyy-MM-ddTHH:mm:ss",
      "seats": [...]
    }
  ]
  ```
- **Status Code**: `200 OK`

---

### 4. Get All Trains with Seats

**Endpoint**: `GET /trains/with-seats`

#### Description:
Retrieve all trains along with their seats.

#### Response:

- **Body**:
  ```json
  [
    {
      "trainId": "long",
      "trainName": "string",
      "fromPlace": "string",
      "toPlace": "string",
      "departureTime": "yyyy-MM-ddTHH:mm:ss",
      "seats": [...]
    }
  ]
  ```
- **Status Code**: `200 OK`

---

### 5. Set Seat Status and Calculate Total Cost

**Endpoint**: `PUT /trains/setStatus`

#### Description:
Set the status of one or more seats and calculate the total cost of the seats with the specified status.

#### Request Body:

```json
{
  "seatIds": ["long", "long"],
  "status": "string"
}
```

#### Response:

- **Body**:
  ```json
  {
    "totalCost": "long"
  }
  ```
- **Status Code**: `200 OK`

---

## Authorization

Currently, the Trains Microservice API does not require authentication for accessing its endpoints. All endpoints are publicly accessible.

---


# Payment Microservice API

This documentation provides an overview of the **Payment Microservice**. The service is responsible for handling payment creation, retrieving tickets associated with a user, and managing ticket deletion. 

## Table of Contents

- [Installation](#installation)
- [API Endpoints](#api-endpoints)
  - [Create Payment](#1-create-payment)
  - [Get Tickets by User](#2-get-tickets-by-user)
  - [Get Ticket by ID for a User](#3-get-ticket-by-id-for-a-user)
  - [Delete Ticket by ID](#4-delete-ticket-by-id)
- [Authorization](#authorization)

---

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-repository/payment-microservice.git
    ```

2. Navigate to the project directory:

    ```bash
    cd payment-microservice
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application will run locally at `http://localhost:8080`.

---

## API Endpoints

### 1. Create Payment

**Endpoint**: `POST /payment`

#### Description:
This endpoint allows the creation of a payment for a user by specifying user ID, booking ID, and a list of passengers. Upon successful creation, the payment details are returned.

#### Request Body:

```json
{
  "userId": "long",
  "bookingId": "long",
  "passengerList": ["string"]
}
```

#### Response:

- **Body**:
  ```json
  {
    "paymentId": "long",
    "userId": "long",
    "bookingId": "long",
    "amount": "long"
  }
  ```
- **Status Code**: `201 Created`

#### Error Response:

- **Body**: `"Error message"`
- **Status Code**: `400 Bad Request`

---

### 2. Get Tickets by User

**Endpoint**: `GET /payment/ticket`

#### Description:
Retrieve a list of tickets associated with a specific user by providing their user ID.

#### Request Body:

```json
{
  "userId": "long"
}
```

#### Response:

- **Body**:
  ```json
  [
    {
      "ticketId": "long",
      "userId": "long",
      "passengerList": ["string", "string"],
      "seatId": ["long"],
      "amount": "long",
      "bookingId": "long",
      "paymentId": "long"
    }
  ]
  ```
- **Status Code**: `200 OK`

#### Error Response:

- **Status Code**: `404 Not Found`

---

### 3. Get Ticket by ID for a User

**Endpoint**: `GET /payment/ticket/{ticketId}`

#### Description:
Retrieve a specific ticket by its ID and the user associated with it. You must provide the user ID in the request body and the ticket ID in the path variable.

#### Path Variable:
- `ticketId`: Ticket's ID (Long)

#### Request Body:

```json
{
  "userId": "long"
}
```

#### Response:

- **Body**:
  ```json
  {
    "ticketId": "long",
    "userId": "long",
    "passengerList": ["string"],
    "seatId": ["long"],
    "amount": "long",
    "bookingId": "long",
    "paymentId": "long"
  }
  ```
- **Status Code**: `200 OK`

#### Error Response:

- **Status Code**: `404 Not Found`

---

### 4. Delete Ticket by ID

**Endpoint**: `DELETE /payment/ticket/{ticketId}`

#### Description:
Delete a specific ticket by providing the user ID and the ticket ID.

#### Path Variable:
- `ticketId`: Ticket's ID (Long)

#### Request Body:

```json
{
  "userId": "long"
}
```

#### Response:

- **Body**: `"Ticket deletion was successful"`
- **Status Code**: `200 OK`

#### Error Response:

- **Status Code**: `404 Not Found`

---

## Authorization

Currently, the Payment Microservice API does not require authentication for accessing its endpoints. All endpoints are publicly accessible.

---

# Booking Microservice API

This documentation provides an overview of the **Booking Microservice**. The service is responsible for managing user bookings, verifying bookings using OTP, and retrieving booking details.

## Table of Contents

- [API Endpoints](#api-endpoints)
  - [Add Booking](#1-add-booking)
  - [Verify Booking with OTP](#2-verify-booking-with-otp)
  - [Get Booking by ID](#3-get-booking-by-id)

---

## API Endpoints

### 1. Add Booking

**Endpoint**: `POST /booking`

#### Description:
This endpoint allows a user to create a booking by providing their user ID and a list of seat IDs. Upon successful booking, the booking ID is returned.

#### Request Body:

```json
{
  "userId": "long",
  "seatIds": ["long"]
}
```

#### Response:

- **Body**:
  ```json
  {
    "bookingId": "long"
  }
  ```
- **Status Code**: `202 Accepted`

#### Error Response:

- **Body**: `"Booking wasn't successful!"`
- **Status Code**: `404 Not Found`

---

### 2. Verify Booking with OTP

**Endpoint**: `POST /booking/verify`

#### Description:
This endpoint allows the user to verify their booking by providing the OTP (One-Time Password) sent to their registered contact details. If the OTP is correct, the booking is confirmed.

#### Request Body:

```json
{
  "userId": "long",
  "bookingId": "long",
  "otp": "long"
}
```

#### Response:

- **Body**: `true` (if verified) or `false` (if verification failed)
- **Status Code**: `202 Accepted`

---

### 3. Get Booking by ID

**Endpoint**: `GET /booking/{id}`

#### Description:
Retrieve the details of a booking using its booking ID.

#### Path Variable:

- `id`: The ID of the booking.

#### Response:

- **Body**:
  ```json
  {
    "bookingId": "long",
    "userId": "long",
    "seatIds": ["long"],
    "status": "string"
  }
  ```
- **Status Code**: `200 OK`

#### Error Response:

- **Status Code**: `404 Not Found`

---

