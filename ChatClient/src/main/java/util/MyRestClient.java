package util;
import model.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyRestClient {
    private RestTemplate restTemplate;

    public MyRestClient() {
        restTemplate = new RestTemplate();
    }
    public void sendMessage(MessageDto messageDto){
        restTemplate.postForObject("http://localhost:8080/messages", messageDto, MessageDto.class);
    }
    public MessageDto[] getMessages(){
        ResponseEntity<MessageDto[]> response = restTemplate.getForEntity("http://localhost:8080/messages", MessageDto[].class);
        MessageDto[] messages = response.getBody();
        return messages;
    }
}
