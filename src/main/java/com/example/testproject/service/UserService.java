package com.example.testproject.service;

import com.example.testproject.entity.Message;
import com.example.testproject.entity.User;
import com.example.testproject.model.MessageForm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> selectUserFromDbByUserName(String username);

    Message saveMessage(MessageForm messageForm);

    List<Message> getTenLastMessage(MessageForm messageForm);
}
