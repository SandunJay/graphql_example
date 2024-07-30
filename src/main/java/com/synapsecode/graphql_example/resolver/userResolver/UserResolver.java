package com.synapsecode.graphql_example.resolver.userResolver;

import com.synapsecode.graphql_example.input.PostInput;
import com.synapsecode.graphql_example.input.UserInput;
import com.synapsecode.graphql_example.model.Post;
import com.synapsecode.graphql_example.model.User;
import com.synapsecode.graphql_example.repository.PostRepository;
import com.synapsecode.graphql_example.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Post createPost(String title, String content, Long authorId) {
        User author = userRepository.findById(authorId).orElse(null);
        if (author == null) {
            throw new RuntimeException("Author not found");
        }
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public User updateUser(Long id, String name) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setName(name);
        return userRepository.save(user);
    }
}
