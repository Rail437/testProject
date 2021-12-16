package com.example.testproject.service;


import com.example.testproject.entity.Message;
import com.example.testproject.entity.Role;
import com.example.testproject.entity.User;
import com.example.testproject.model.MessageForm;
import com.example.testproject.repository.MessageRepository;
import com.example.testproject.repository.RoleRepository;
import com.example.testproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostgresUserService implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageRepository messageRepository;

    @Override
    public Optional<User> selectUserFromDbByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Message saveMessage(MessageForm messageForm) {
        User user = userRepository.findByUsername(messageForm.getUsername()).orElseThrow();
        Message message = new Message(messageForm.getMessage(),user);
        user.addMessege(message);
        userRepository.save(user);
        //messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getTenLastMessage(MessageForm messageForm) {
        List<Message> messages = messageRepository.findTop10ByUserUsernameOrderByIdDesc(messageForm.getUsername());
        return messages;
    }


    /**
     * Этот метод я сделал просто для инициализации первого пользователя для теста.
     */
    @Autowired
    private void init(){
        saveRole();
        Optional<User> user = userRepository.findByUsername("user");
        if(user.isEmpty()) {
            userRepository.save(new User(
                    "user",
                    passwordEncoder.encode("user"),
                    Set.of(new Role(1L, "USER"))
            ));
        }
    }

    private void saveRole() {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Role(1L, "USER"));
        }
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role(2L, "ADMIN"));
        }
    }
}
