package controller;

import dto.ChatDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**s
 * Created by KFU-user on 24.11.2016.
 */
@RestController
public class ChatController {
    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public List<ChatDto> getChats(){
        return null;
    }
}
