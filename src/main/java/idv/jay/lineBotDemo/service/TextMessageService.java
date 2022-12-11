package idv.jay.lineBotDemo.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import idv.jay.lineBotDemo.repository.MessageRepository;
import idv.jay.lineBotDemo.repository.entity.Message;
import idv.jay.lineBotDemo.repository.entity.TextMessage;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TextMessageService implements MessageService {

    private static final String TYPE = "text";
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(MessageEvent event) {

        if(!(event.getMessage() instanceof TextMessageContent))
            return;

        TextMessageContent messageContent = (TextMessageContent) event.getMessage();

        Message<TextMessage> message = Message.<TextMessage>builder()
                .type(TYPE)
                .userId(event.getSource().getUserId())
                .messageContent(this.buildMessageContent(messageContent))
                .build();
        messageRepository.saveMessage(message);
    }

    private TextMessage buildMessageContent(TextMessageContent textMessageContent) {

        List<TextMessageContent.Emoji> inputEmojis = textMessageContent.getEmojis();
        List<TextMessage.Emoji> emojis = new ArrayList<>();
        if(inputEmojis != null) {
            emojis = inputEmojis.stream()
                    .map(e -> TextMessage.Emoji.builder()
                            .emojiId(e.getEmojiId())
                            .index(e.getIndex())
                            .productId(e.getProductId())
                            .build()
                    )
                    .collect(Collectors.toList());
        }

        return TextMessage.builder()
                .text(textMessageContent.getText())
                .emojis(emojis)
                .build();
    }
}
