package ru.itis.animerec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.animerec.dto.MessageDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.MessageEntity;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.repository.MessageRepository;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.ChatService;
import ru.itis.animerec.utils.mapper.MessageMapper;
import ru.itis.animerec.utils.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;


    @Override
    public MessageDTO saveMessage(MessageDTO messageDTO, Authentication authentication) {
        log.info("Saving message: {}", messageDTO);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();
        MessageEntity messageEntity = messageMapper.toEntity(messageDTO);
        messageEntity.setUser(user);
        MessageEntity savedMessageEntity = messageRepository.save(messageEntity);
        log.info("Message saved successfully with id: {}", savedMessageEntity.getId());
        return messageMapper.toDTO(savedMessageEntity);
    }

    @Override
    @Transactional
    public List<MessageDTO> getLast100Messages() {
        log.info("Fetching last 100 messages");
        List<MessageEntity> last100Messages = messageRepository.findTop100ByOrderByTimestampAsc();
        log.info("Fetched {} messages", last100Messages.size());
        return last100Messages.stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }
}