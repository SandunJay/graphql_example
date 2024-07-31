
##REST endpoints##
Testing the Endpoints

You can now run the application and test both REST and GraphQL endpoints.

- **REST API Testing**: Use tools like Postman to test REST endpoints at `http://localhost:8080/api/products`.

- **GraphQL API Testing**: Use a GraphQL client or IDE plugin to test GraphQL queries and mutations at `http://localhost:8080/graphql`.


##GraphQL queries##
# Query to get all products
{
  products {
    id
    name
    description
    price
  }
}

# Query to get a product by ID
{
  product(id: 1) {
    id
    name
    description
    price
  }
}

# Mutation to create a new product
mutation {
  createProduct(name: "New Product", description: "A new product", price: 99.99) {
    id
    name
    description
    price
  }
}

# Mutation to update a product
mutation {
  updateProduct(id: 1, name: "Updated Product", description: "Updated description", price: 199.99) {
    id
    name
    description
    price
  }
}

# Mutation to delete a product
mutation {
  deleteProduct(id: 1)
}