package ru.itis.animerec.service;

import org.springframework.security.core.Authentication;
import ru.itis.animerec.dto.MessageDTO;
import ru.itis.animerec.dto.UserDTO;

import java.util.List;

public interface ChatService {
    MessageDTO saveMessage(MessageDTO messageDTO, Authentication authentication);
    List<MessageDTO> getLast100Messages();
}
