package idv.jay.lineBotDemo.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class SendMessageRequest {

    private String userId;
    private List<String> messages;
}
