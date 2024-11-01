
![BANNER](https://github.com/user-attachments/assets/be5b9826-8cbd-45a5-b1c5-faabe60e27a9)

# StreetSyntax - Clothing E-commerce Platform

StreetSyntax is a Spring Boot-based e-commerce platform focused on delivering a streamlined shopping experience. Users can register, log in, browse products, and manage their own shopping cart. Admin users have dedicated access for managing product data.

## Features

![CleanShot 2024-10-19 at 17 05 39@2x](https://github.com/user-attachments/assets/a523cb3a-f1f8-447c-b9fd-2060c657bc49)

### 1. User Registration and Login
- **Register**: A new user can register through the `/api/users/register` endpoint by providing a `username`, `email`, and `password`.
- **Password Encoding**: During registration, the password is securely encoded.
- **Default Role Assignment**: New users are assigned the default role of **USER**.
- **Login**: Users log in via `/api/users/login`. On successful login, a session attribute `AUTHENTICATED_USER` is set with the user’s email to track their logged-in state.

### 2. User Session Management
- **Session Storage**: After a successful login, the user's session is stored on the server, enabling actions like viewing products and managing the shopping cart.
- **Logout**: A logout endpoint (`/api/users/logout`) is provided to securely end the session and clear cookies.

### 3. Product Management
- **Admin Control**: Admin users can manage product data through endpoints under `/api/product`. Available operations include:
  - **Add Product**: `/add` - Adds a new product.
  - **Delete Product**: `/delete/{prodId}` - Deletes a product by its ID.
  - **View Products**: Fetch all products or specific products by ID.

### 4. Shopping Cart Management
- **Add Items**: Authenticated users can add items to their cart using the `/api/cart` endpoint by specifying a `product ID` and `quantity`.
- **Session-Based Cart**: Cart items are specific to the user's session, ensuring each user has a personalized cart.
- **View and Remove Items**: Users can view all items in their cart or remove items using `/api/cart/remove/{cartItemId}`.

## Technologies Used
- **Backend**: Spring Boot
- **Database**: MySQL
- **Security**: Spring Security
- **Password Encoding**: BCryptPasswordEncoder



# Screenshots #

![CleanShot 2024-11-01 at 22 20 57](https://github.com/user-attachments/assets/90e1f179-04c0-4913-aad3-d8d02b864e4a)

