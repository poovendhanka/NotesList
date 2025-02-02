package com.notes.NotesList.Service;

import com.notes.NotesList.model.User;
import com.notes.NotesList.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Use proper logging for better performance and debugging
        System.out.println("Searching for user: " + username);

        // Fetch the user from the database
        Optional<User> userOptional = userRepo.findByUsername(username);

        // If the user doesn't exist, throw UsernameNotFoundException
        if (userOptional.isEmpty()) {
            System.out.println("User not found: " + username);  // Log if user is not found
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();  // Get the user from the Optional

        // Log the found user
        System.out.println("User found: " + user.getUsername());

        // Return the UserDetails object (custom User object can be used here)
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
