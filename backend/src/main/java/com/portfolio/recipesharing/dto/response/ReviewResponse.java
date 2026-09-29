package com.portfolio.recipesharing.dto.response;

import com.portfolio.recipesharing.entity.Review;
import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Integer rating,
        String comment,
        String commentPt,
        String authorUsername,
        LocalDateTime createdAt
) {
    public static ReviewResponse from(Review r) {
        return new ReviewResponse(
                r.getId(), r.getRating(), r.getComment(), r.getCommentPt(),
                r.getAuthor().getUsername(), r.getCreatedAt()
        );
    }
}
