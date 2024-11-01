
![BANNER](https://github.com/user-attachments/assets/be5b9826-8cbd-45a5-b1c5-faabe60e27a9)

This project is an e-commerce backend system built with Spring Boot. It includes user management, product management, and a cart system where users can add or remove products. The project uses Spring Security to secure the endpoints based on roles, with ROLE_USER and ROLE_ADMIN distinguishing regular users and administrators. The system leverages BCrypt for password encryption, ensuring secure authentication. Additionally, it includes exception handling and logging to provide meaningful responses and track operations.

#Project Flow

![CleanShot 2024-10-19 at 17 05 39@2x](https://github.com/user-attachments/assets/f8ad6baa-4a6a-4249-8c5e-d12f0fb08c4a)

**User Registration & Login:**
	*Users register by providing a username, email, and password. Their password is securely stored using BCrypt hashing.
	*During login, the entered password is compared with the stored hashed password. If successful, the user is authenticated.

**Product Management:**
	*Admin-protected endpoints allow authorized users to add, delete, and view products.
	*Products have attributes such as prodId, prodName, prodDesc, price, quantity, and a list of imageUrls.
	*Only users with the ROLE_ADMIN can access endpoints to manage products.

**Cart Management:**
	*Authenticated users can add products to their cart, view all items in their cart, or remove items by their cartItemId.
	*The cart is associated with both the product and the user, linking each cart item to a specific user and product.

**Security:**
	*Spring Security configurations enforce role-based access to specific endpoints. For instance:
		-/api/product/** endpoints are accessible only to ROLE_ADMIN.
		-/api/cart/** requires authentication, ensuring only logged-in users can interact with their cart.
	*Passwords are encrypted using BCrypt via a PasswordEncoder bean.


#Screenshots

![CleanShot 2024-10-19 at 17 07 52@2x](https://github.com/user-attachments/assets/e1ca22dd-e99b-4ef1-839f-cc8f33b84f18)
