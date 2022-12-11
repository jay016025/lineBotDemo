package idv.jay.lineBotDemo.config;

import idv.jay.lineBotDemo.repository.MessageRepository;
import idv.jay.lineBotDemo.repository.impl.JpaMessageRepositoryImpl;
import idv.jay.lineBotDemo.repository.jpa.JpaMessageRepository;
import idv.jay.lineBotDemo.service.MessageServiceFacade;
import idv.jay.lineBotDemo.service.StickerMessageService;
import idv.jay.lineBotDemo.service.TextMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageRepository messageRepository(JpaMessageRepository jpaMessageRepository) {
        return new JpaMessageRepositoryImpl(jpaMessageRepository);
    }

    @Bean
    public TextMessageService textMessageService(MessageRepository messageRepository) {
        return new TextMessageService(messageRepository);
    }

    @Bean
    public StickerMessageService stickerMessageService(MessageRepository messageRepository) {
        return new StickerMessageService(messageRepository);
    }

    @Bean
    public MessageServiceFacade messageServiceFacade(MessageRepository messageRepository) {
        return new MessageServiceFacade(messageRepository);
    }
}
