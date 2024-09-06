package ru.itis.animerec.service;

public interface FeedbackService {
    void likeAnime(Long animeId);
    void dislikeAnime(Long animeId);
}