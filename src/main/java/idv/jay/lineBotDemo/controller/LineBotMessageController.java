package idv.jay.lineBotDemo.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import idv.jay.lineBotDemo.controller.request.SendMessageRequest;
import idv.jay.lineBotDemo.service.MessageServiceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@LineMessageHandler
public class LineBotMessageController {

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Autowired
    private MessageServiceFacade messageServiceFacade;

    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody SendMessageRequest request) {
        List<Message> messages = request.getMessages().stream()
                .map(m -> new TextMessage(m)).collect(Collectors.toList());
        PushMessage pushMessage = new PushMessage(request.getUserId(), messages);
        lineMessagingClient.pushMessage(pushMessage);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        messageServiceFacade.saveMessage(event);
        return new TextMessage("儲存訊息成功!!");
    }

    @EventMapping
    public Message handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
        log.info("event: " + event);
        messageServiceFacade.saveMessage(event);
        return new TextMessage("儲存訊息成功!!");
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
