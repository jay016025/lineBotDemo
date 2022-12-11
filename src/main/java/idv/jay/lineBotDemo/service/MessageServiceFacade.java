package idv.jay.lineBotDemo.service;

import com.linecorp.bot.model.event.MessageEvent;
import idv.jay.lineBotDemo.repository.MessageRepository;
import idv.jay.lineBotDemo.repository.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MessageServiceFacade implements BeanPostProcessor {

    private final MessageRepository messageRepository;

    private List<MessageService> serviceList = new ArrayList<>();

    public void saveMessage(MessageEvent event) {
        serviceList.stream().forEach(e -> e.saveMessage(event));
    }

    public List<Message> findMessagesByUserId(String userId) {
        return messageRepository.findMessageByUserId(userId);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MessageService)
            serviceList.add((MessageService) bean);
        return bean;
    }
}
