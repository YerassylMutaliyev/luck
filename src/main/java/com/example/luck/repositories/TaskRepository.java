package com.example.luck.repositories;

import com.example.luck.model.Task;
import com.example.luck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user); // Fetch tasks by user
    List<Task> findByUserAndStatus(User user, String status); // Filter tasks by status
    Page<Task> findByUser(User user, Pageable pageable); // Paginate tasks for user
    Page<Task> findByUserId(Long userId, Pageable pageable);
    List<Task> findByUserId(Long userId);
}

