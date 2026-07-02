# Service Booking and Vendor Management System

A secure and scalable backend application built using **Spring Boot** for managing service providers, customer bookings, vendor verification, and administrator approval workflows.

The system provides a complete vendor onboarding process, including document verification through Cloudinary, role-based authentication using JWT, and REST APIs documented with Swagger/OpenAPI.

---

## Project Overview

The Service Booking and Vendor Management System is designed to simplify the process of connecting customers with verified service providers.

Customers can register and book services, vendors can apply by submitting verification documents, and administrators can review applications before approving vendors to operate on the platform.

**Current Version:** Each vendor can register and manage a single service offering. Support for multiple services per vendor is planned for a future release.

The project follows RESTful API principles and implements secure authentication, cloud-based document storage, and cloud deployment. 

---

## Features

### Authentication & Authorization

- User Registration
- User Login
- JWT Authentication
- Role-Based Access Control (RBAC)
- Secure Password Encryption using BCrypt

---

### Customer Module

- Register Customer
- Login Customer
- Book Services
- View Booking History
- Cancel Bookings

---

### Vendor Module

- Vendor Registration
- Upload Verification Documents
- Update Vendor Profile
- Manage Vendor Services
- View Vendor Details
- Vendor Dashboard APIs

---

### Admin Module

- View Pending Vendor Requests
- Review Vendor Verification Documents
- Approve Vendor Applications
- Reject Vendor Applications
- Manage Vendors

---

### Vendor Verification

The system verifies vendor identity using:

- Aadhaar Front Image
- Aadhaar Back Image
- Verification Document

All uploaded files are stored securely using **Cloudinary**, while only the generated URLs are stored in the database.

---

### Booking Management

- Create Booking
- Update Booking Status
- View Customer Bookings
- View Vendor Bookings
- Cancel Booking

---

## System Architecture

```
Client
       │
       ▼
Spring Boot REST API
       │
 ┌───────────────┐
 │ JWT Security  │
 └───────────────┘
       │
       ▼
Business Services
       │
       ▼
MySQL / TiDB Cloud Database

       │

Cloudinary
(Document Storage)
```

---

## Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

### Database

- MySQL
- TiDB Cloud (Production)

### Authentication

- JWT (JSON Web Token)
- BCrypt Password Encryption

### Cloud Services

- Cloudinary
- Render

### Documentation

- Swagger / OpenAPI 3

### Build Tool

- Maven

### Deployment

- Docker
- Render

---

## Database Design

Major entities include:

- User
- Vendor
- Vendor Service
- Booking
- Service Category
- Address

The application follows proper relational mapping using JPA/Hibernate.

---

## API Documentation

Swagger UI is available after deployment.

```
/swagger-ui/index.html
```

---

## Security Features

- JWT Authentication
- Password Encryption
- Role-Based Authorization
- Protected APIs
- Vendor Approval Workflow
- Secure Document Storage

---

## Cloud Deployment

### Backend

Hosted on Render

### Database

Hosted on TiDB Cloud

### Document Storage

Cloudinary

---

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── config
 ├── security
 ├── exception
 └── util
```

---

## Running the Project Locally

### Clone Repository

```bash
git clone https://github.com/Bittu949/Service-Booking-and-Vendor-Management-System.git
```

### Navigate to Project

```bash
cd Service-Booking-and-Vendor-Management-System
```

### Configure Environment Variables

Configure the following properties:

```
SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

CLOUDINARY_CLOUD_NAME

CLOUDINARY_API_KEY

CLOUDINARY_API_SECRET
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

---

## Docker Support

Build Docker Image

```bash
docker build -t service-booking .
```

Run Container

```bash
docker run -p 8080:8080 service-booking
```

---

## Future Enhancements

The current version of the system provides a complete workflow for customer registration, vendor onboarding, service booking, and administrator approval. The following enhancements are planned for future releases:

- Support for **Multiple Vendor Services** (Currently, a vendor can register only one service. Future versions will allow vendors to offer and manage multiple services under a single account.)
- Email Notifications for booking confirmations and vendor approval/rejection.
- Payment Gateway Integration for secure online payments.
- Customer Ratings and Reviews for completed services.
- Vendor Availability Calendar and Schedule Management.
- Real-time Notifications for customers and vendors.
- Admin Dashboard with analytics and reporting.
- Service Recommendation System.
- Booking History Export (PDF/Excel).
- Mobile Application support using the same REST APIs.
- Audit Logging for administrative actions.
- Enhanced Search and Filtering for vendors and services.

---

## Author

**Balkrishna Naik**

Backend Developer

GitHub:
https://github.com/Bittu949

---