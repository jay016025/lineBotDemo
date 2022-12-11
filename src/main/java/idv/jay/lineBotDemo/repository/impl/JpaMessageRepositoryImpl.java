package idv.jay.lineBotDemo.repository.impl;

import idv.jay.lineBotDemo.repository.MessageRepository;
import idv.jay.lineBotDemo.repository.entity.Message;
import idv.jay.lineBotDemo.repository.jpa.JpaMessageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JpaMessageRepositoryImpl implements MessageRepository {

    private final JpaMessageRepository jpaMessageRepository;

    @Override
    public void saveMessage(Message message) {
        jpaMessageRepository.save(message);
    }

    @Override
    public List<Message> findMessageByUserId(String userId) {
        return jpaMessageRepository.findByUserId(userId);
    }
}
