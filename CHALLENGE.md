# Back-end Homework 57Blocks
Pick one of these topics for the test:
- Restaurants
- **Movies**
- Pokemon
- Music

Implement an HTTP server API (REST or GraphQL) that fulfills the following requirements:
Requirements:
1. Create a registration service that receives an email and a password:
   - Validate email is a valid email address.
   - Validate email is not already registered in the database.
   - Validate password contains at least 10 characters, one lowercase letter, one uppercase letter and one of the following characters: !, @, #, ? or ].
   - If any of the above is not valid, send back a meaningful response.
2. Allow login into the server with an email and a password:
   - Validate email is a valid email address
   - Validate email is already registered in the database
   - Validate password contains at least 10 characters, one lowercase letter, one uppercase letter and one of the following characters: !, @, #, ? or ].
   - Validate email and password matches for a previous registered user.
   - If any of the above is not valid send back a meaningful response.
   - If all of the above are valid send back a payload including some way for users to identify themselves for subsequent requests. That way to identify users should be invalid after 20 minutes and the user must login again to continue communication
   with the server.
3. Allow logged-in users to do CRUD operations into a table/collection of the topic you
   picked above:
   - Users should be able to create a new element that can only be retrieved by
   themselves (Private item), or that can be retrieved by others (Public item).
   - Users should be able to read all public elements in the table/collection.
   - Users should be able to read all elements created by themselves.
   - Users should be able to edit at least one field in one of their private items.
   - Validate that users are trying to read or update their own private elements,
   otherwise send a meaningful response.
## Must have for this test:
1. A git repository with the code and a README.md explaining how to run the code in the
   reviewer computer with very clear steps.
2. Create the models for the selected topic with at least 5 meaningful fields.
3. Prefill the public elements with a list you built previously
4. Read requests must support pagination

## Nice to have for this test:
1. The API is deployed in a cloud provider (Heroku, AWS, GCP, etc. offer free tiers for
   simple deployments)
2. Unit testing
3. Implement what you would consider a good architecture.
4. Implement requirements using well known standards.
5. Create a meaningful documentation for potential clients that would use the API
6. Add an endpoint that requires your server to retrieve a random number from a public API
   and send it back to the user.