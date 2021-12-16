package com.example.testproject.controller;

import com.example.testproject.entity.Message;
import com.example.testproject.model.MessageForm;
import com.example.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class UserController {
    private final static String HISTORY = "history 10";
    private final UserService userService;

    @PostMapping
    public List<Message> addMessage(@RequestBody MessageForm messageForm){
        if(messageForm.getMessage().equals(HISTORY)) {
            System.out.println(messageForm.getMessage());
            return userService.getTenLastMessage(messageForm);
        }else {
            Message message = userService.saveMessage(messageForm);
            return List.of(message);
        }
    }
}
