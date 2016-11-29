package controller;

import converter.ConverterDto;
import dto.ChatDto;
import model.Chat;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ChatService;
import service.UserService;
import utils.Verifier;

import java.util.ArrayList;
import java.util.List;

/**s
 * Created by KFU-user on 24.11.2016.
 */
@RestController
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    ConverterDto converter = new ConverterDto();

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public List<ChatDto> getChats(){
        List<ChatDto> listChatDto = new ArrayList<>();
        List<Chat> listChat = chatService.findAll();
        for(Chat chat : listChat){
            listChatDto.add(converter.convert(chat));
        }
        return listChatDto;
    }
    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public Integer postChats(@RequestBody String chatName, @RequestHeader String token){
        User user = userService.findByToken(token);
        if (user != null){
            Chat chat = Chat.newBuilder().setName(chatName).setOwnerId(user.getId()).build();
            chatService.save(chat);
            return chatService.findByName(chatName).getId();
        }else{
            return null;
        }
    }
}
