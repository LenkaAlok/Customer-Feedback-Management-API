package com.crudoperation.Crudoperation.repository;

import com.crudoperation.Crudoperation.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Feedback> findByIdAndUserId(Long feedbackId, Long userId);
}