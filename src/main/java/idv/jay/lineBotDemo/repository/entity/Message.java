package idv.jay.lineBotDemo.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Data
@Document("message")
public class Message<MC extends MessageContent> {

    @MongoId
    private String id;
    @Indexed
    private String userId;
    private String type;
    private MC messageContent;
}
