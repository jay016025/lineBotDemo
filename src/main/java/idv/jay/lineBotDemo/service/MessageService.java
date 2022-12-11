package idv.jay.lineBotDemo.service;

import com.linecorp.bot.model.event.MessageEvent;

public interface MessageService {

    void saveMessage(MessageEvent event);
}
