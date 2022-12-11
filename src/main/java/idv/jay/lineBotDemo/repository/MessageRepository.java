package idv.jay.lineBotDemo.repository;

import idv.jay.lineBotDemo.repository.entity.Message;

import java.util.List;

public interface MessageRepository {

    void saveMessage(Message message);

    List<Message> findMessageByUserId(String userId);
}
