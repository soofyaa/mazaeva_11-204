package ru.itis.animerec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private Long userId;
    private String username;
    private String userAvatarBase64;
    private String content;
    private LocalDateTime timestamp;
}
