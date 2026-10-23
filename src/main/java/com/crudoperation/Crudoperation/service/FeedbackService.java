package com.crudoperation.Crudoperation.service;

import com.crudoperation.Crudoperation.dto.FeedbackRequest;
import com.crudoperation.Crudoperation.entity.Feedback;
import com.crudoperation.Crudoperation.entity.User;
import com.crudoperation.Crudoperation.repository.FeedbackRepository;
import com.crudoperation.Crudoperation.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
    }

    private User getLoggedInUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));
    }

    @Transactional
    public Feedback addFeedback(FeedbackRequest request) {
        User user = getLoggedInUser();

        Feedback feedback = new Feedback();
        feedback.setMessage(request.getMessage());
        feedback.setUser(user);

        return feedbackRepository.save(feedback);
    }

    @Transactional(readOnly = true)
    public List<Feedback> getMyFeedback() {
        User user = getLoggedInUser();

        return feedbackRepository.findByUserIdOrderByCreatedAtDesc(
                user.getId());
    }

    @Transactional
    public Feedback updateFeedback(
            Long feedbackId,
            FeedbackRequest request) {

        User user = getLoggedInUser();

        Feedback feedback = feedbackRepository
                .findByIdAndUserId(feedbackId, user.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Feedback not found or access denied"));

        feedback.setMessage(request.getMessage());

        return feedbackRepository.save(feedback);
    }
}
