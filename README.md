HOW TO RUN:
1. Navigate into backend
cd backend

2. Run Spring Boot
mvn clean compile
mvn spring-boot:run

runs at:
http://localhost:8080

3. Access H2 Console (DEV ONLY)
http://localhost:8080/h2-console


JDBC URL:

jdbc:h2:mem:campusconnect

STRUCTURE:
backend/
 ├── controller/     # API endpoints
 ├── service/        # Business logic
 ├── repository/     # DB access (Spring Data JPA)
 ├── model/          # Entities (Users, Groups, Posts, Messages, Notifications)
 ├── dto/            # Request/response objects
 └── config/         # SecurityConfig, global exception handler

API SUMMARY:

REGISTER

-POST /api/auth/register
-Body:
{
  "name": "Nick",
  "email": "Nick@example.com",
  "password": "123",
  "major": "CS",
  "bio": "Hello"
}
-Returns:
{
  "userId": "uuid",
  "name": "Nick",
  "email": "Nick@example.com"
}

LOGIN

-POST /api/auth/login
-Body:
{
  "email": "Nick@example.com",
  "password": "123"
}

GROUPS API

Get all groups:
-GET /api/groups

Create group:
-POST /api/groups
-Body:
{
  "name": "Test Group",
  "subject": "Test Subject",
  "description": "Test Description",
  "createdBy": "USER_ID"     taken from logged-in user
}

MEMBERSHIP

Join a Group
-POST /api/groups/{groupId}/join
-Body:
{ "userId": "USER_ID" }

Leave a Group
-POST /api/groups/{groupId}/leave
-Body:
{ "userId": "USER_ID" }

Get User's Group
-GET /api/users/{userId}/groups

MESSAGING

Send Direct message
-POST /api/messages
-Body:
{
  "senderId": "uuid",
  "receiverId": "uuid",
  "content": "Test Message"
}

View Direct message history
-GET /api/messages/direct?user1=UUID&user2=UUID

Group Messages
-GET /api/messages/group/{groupId}

NOTIFICATIONS

Get all notifications
-GET /api/users/{userId}/notifications

Get unread notifications
-GET /api/users/{userId}/notifications/unread

Mark notification as read
-POST /api/notifications/{notifId}/read

SEARCH

Search groups
-GET /api/search/groups?query=TEXT

Search users
-GET /api/search/users?query=TEXT

BACKEND TESTING:
Register a user > store the userId

Login to confirm password hashing works

Create a group using createdBy = userId

Join/leave group

Send yourself a direct message > check notifications

Search groups/users


