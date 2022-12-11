package idv.jay.lineBotDemo.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TextMessage implements MessageContent{

    private String text;
    private List<Emoji> emojis;

    @Data
    @Builder
    public static class Emoji {
        private Integer index;
        private String productId;
        private String emojiId;
    }
}
