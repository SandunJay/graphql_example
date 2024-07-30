package com.synapsecode.graphql_example.service;

import com.synapsecode.graphql_example.model.Post;
import com.synapsecode.graphql_example.model.User;
import com.synapsecode.graphql_example.repository.PostRepository;
import com.synapsecode.graphql_example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            return userRepository.save(existingUser);
        }
        return null;
    }
}
