package idv.jay.lineBotDemo.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import idv.jay.lineBotDemo.repository.MessageRepository;
import idv.jay.lineBotDemo.repository.entity.Message;
import idv.jay.lineBotDemo.repository.entity.StickerMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StickerMessageService implements MessageService {

    private static final String TYPE = "sticker";

    private final MessageRepository messageRepository;
    @Override
    public void saveMessage(MessageEvent event) {

        if(!(event.getMessage() instanceof StickerMessageContent))
            return;

        StickerMessageContent messageContent = (StickerMessageContent) event.getMessage();

        Message<StickerMessage> message = Message.<StickerMessage>builder()
                .type(TYPE)
                .userId(event.getSource().getUserId())
                .messageContent(this.buildMessageContent(messageContent))
                .build();
        messageRepository.saveMessage(message);
    }

    private StickerMessage buildMessageContent(StickerMessageContent messageContent) {
        return StickerMessage.builder()
                .packageId(messageContent.getPackageId())
                .stickerId(messageContent.getStickerId()).build();
    }
}
