package com.crudoperation.Crudoperation.controller;

import com.crudoperation.Crudoperation.dto.FeedbackRequest;
import com.crudoperation.Crudoperation.entity.Feedback;
import com.crudoperation.Crudoperation.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Feedback> addFeedback(
            @Valid @RequestBody FeedbackRequest request) {

        Feedback feedback = feedbackService.addFeedback(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Feedback>> getMyFeedback() {

        List<Feedback> feedbackList = feedbackService.getMyFeedback();

        return ResponseEntity.ok(feedbackList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequest request) {

        Feedback feedback = feedbackService.updateFeedback(id, request);

        return ResponseEntity.ok(feedback);
    }
}