package ru.itis.animerec.controller.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.animerec.dto.MessageDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.ChatService;
import ru.itis.animerec.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService messageService;

    @GetMapping()
    @Operation(summary = "Открыть чат", description = "Открывает страницу чата.")
    public String chat() {
        return "/chat/chat";
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageDTO sendMessage(MessageDTO messageDTO, Authentication authentication) {
        return messageService.saveMessage(messageDTO, authentication);
    }

    @MessageMapping("/loadMessages")
    @SendTo("/topic/history")
    @Operation(summary = "Загрузить сообщения", description = "Загружает последние 100 сообщений чата.")
    public List<MessageDTO> loadMessages() {
        return messageService.getLast100Messages();
    }
}
