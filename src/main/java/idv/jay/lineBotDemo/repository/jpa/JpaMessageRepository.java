package idv.jay.lineBotDemo.repository.jpa;

import idv.jay.lineBotDemo.repository.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JpaMessageRepository extends MongoRepository<Message, String> {

    List<Message> findByUserId(String userId);
}
