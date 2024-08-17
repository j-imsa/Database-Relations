# Database Relations


> JPA annotations


Entities:

1. User
2. Address
3. Class
4. Course


Relations:

| # | Entities       | Relation     |
|---|----------------|--------------|
| 1 | User - Address | One-To-One   |
| 2 | User - Class   | One-To-Many  |
| 3 | Class - User   | Many-To-One  |
| 4 | User - Course  | Many-To-Many |


