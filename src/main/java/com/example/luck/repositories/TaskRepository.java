package com.example.luck.repositories;

import com.example.luck.model.Task;
import com.example.luck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user); // Fetch tasks by user
    List<Task> findByUserAndStatus(User user, String status); // Filter tasks by status
    Page<Task> findByUser(User user, Pageable pageable); // Paginate tasks for user
    Page<Task> findByUserId(Long userId, Pageable pageable);
    List<Task> findByUserId(Long userId);

    //filtration
    List<Task> findByPriority(String priority);


    //bounded
    @Query("SELECT t FROM Task t WHERE t.title LIKE %:title% AND t.priority = :priority")
    Page<Task> findByTitleAndPriority(
            @Param("title") String title,
            @Param("priority") String priority,
            Pageable pageable);

    //search
    List<Task> findByTitleContainingIgnoreCase(String title);



}
//second version
//package com.example.luck.repositories;
//
//import com.example.luck.model.Task;
//import com.example.luck.model.User;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<Task> findByUser(User user);
//    Page<Task> findByUserId(Long userId, Pageable pageable);
//    List<Task> findByUserId(Long userId);
//
//    @Query("SELECT t FROM Task t " +
//            "WHERE (:name IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
//            "AND (:status IS NULL OR t.status = :status)")
//    Page<Task> findByNameAndStatus(
//            @Param("name") String name,
//            @Param("status") String status,
//            Pageable pageable
//    );
//
//    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
//    List<Task> findByNameContaining(@Param("name") String name);
//
//    @Query("SELECT t FROM Task t WHERE t.status = :status")
//    List<Task> findByStatus(@Param("status") String status);
//}


