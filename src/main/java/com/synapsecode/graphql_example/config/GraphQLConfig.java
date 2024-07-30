package com.synapsecode.graphql_example.config;

import com.synapsecode.graphql_example.resolver.userResolver.UserResolver;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphQLSchema schema(UserResolver userResolver) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        File schemaFile = new ClassPathResource("schema.graphqls").getFile();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("user", userResolver::getUser))
                .type("Mutation", builder -> {
                    builder.dataFetcher("createPost", environment -> {
                        String title = environment.getArgument("title");
                        String content = environment.getArgument("content");
                        Long authorId = Long.parseLong(environment.getArgument("authorId"));
                        return userResolver.createPost(title, content, authorId);
                    });
                    builder.dataFetcher("updateUser", environment -> {
                        Long id = Long.parseLong(environment.getArgument("id"));
                        String name = environment.getArgument("name");
                        return userResolver.updateUser(id, name);
                    });
                    return builder;
                })
                .build();

        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }
}
