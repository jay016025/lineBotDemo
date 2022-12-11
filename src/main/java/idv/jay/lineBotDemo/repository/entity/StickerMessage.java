package idv.jay.lineBotDemo.repository.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StickerMessage implements MessageContent{
    private String packageId;
    private String stickerId;
}
