package com.example.testproject.repository;

import com.example.testproject.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findTop10ByUserUsernameOrderByIdDesc(String username);
}
