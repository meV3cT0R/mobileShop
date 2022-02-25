package com.vector.mobileshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vector.mobileshop.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
