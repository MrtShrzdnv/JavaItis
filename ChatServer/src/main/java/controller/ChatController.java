package controller;

import converter.ConverterDto;
import dto.ChatDto;
import dto.MessageDto;
import model.Chat;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // get list of all chats
    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public ResponseEntity<List<ChatDto>> getChats(){
        List<ChatDto> listChatDto = new ArrayList<>();
        List<Chat> listChat = chatService.findAll();
        for(Chat chat : listChat){
            listChatDto.add(converter.convert(chat));
        }
        return new ResponseEntity<List<ChatDto>>(listChatDto, HttpStatus.OK);
    }
    // create new chat
    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity<ChatDto> postChats(@RequestBody String chatName, @RequestHeader String token){
        User user = userService.findByToken(token);
        if (user != null){
            Chat chat = Chat.newBuilder().setName(chatName).setOwnerId(user.getId()).build();
            chatService.save(chat);
            return new ResponseEntity<ChatDto>(converter.convert(chatService.findByName(chatName)), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<ChatDto>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/chats/{chat_id}/messages", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getChatsMessages(@RequestHeader String token, @PathVariable("chat_id") String chat_id){
        User user = userService.findByToken(token);
        if (user != null){

            return new ResponseEntity<List<MessageDto>>(HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MessageDto>>(HttpStatus.FORBIDDEN);
        }
    }
}
