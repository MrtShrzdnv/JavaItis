package controller;

import converter.ConverterDto;
import dto.ChatDto;
import dto.MessageDto;
import model.Chat;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ChatService;
import service.MessageService;
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
    @Autowired
    MessageService messageService;
    ConverterDto converter = new ConverterDto();
    Verifier verifier = new Verifier();

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

    // save user to chat
    @RequestMapping(value = "/chats/{chat_id}/members", method = RequestMethod.POST)
    public ResponseEntity<ChatDto> postMembers(@RequestHeader String token, @PathVariable("chat_id") String chat_id){
        User user = userService.findByToken(token);
        if (user != null){
            Integer chatId = Integer.parseInt(chat_id);
            List<Integer> list = userService.findAllUsersIdByChatId(chatId);
            // if this user feat this chat is not exist
            if (!list.contains(user.getId().intValue())){
                userService.saveUserToChat(user.getId(), chatId);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // get all messages
    @RequestMapping(value = "/chats/{chat_id}/messages?get=all", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getAllMessages(@RequestHeader String token, @PathVariable("chat_id") String chat_id){
        if (verifier.verifyTokenIsValid(token)){
            List<Message> messageList = messageService.findAllByChatId(Integer.parseInt(chat_id));
            List<MessageDto> result = new ArrayList<>();
            for(Message message : messageList){
                result.add(converter.convert(message));
            }
            return new ResponseEntity<List<MessageDto>>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MessageDto>>(HttpStatus.FORBIDDEN);
        }
    }

    // post message
    @RequestMapping(value = "/chats/{chat_id}/messages", method = RequestMethod.POST)
    public ResponseEntity<ChatDto> postMessages(@RequestHeader String token, @RequestBody String text,@PathVariable("chat_id") String chat_id){
        User user = userService.findByToken(token);
        if (user != null){
            Message message = Message.newBuilder().setText(text).setUserId(user.getId()).setChatId(Integer.parseInt(chat_id)).build();
            messageService.save(message);
            return new ResponseEntity<ChatDto>(HttpStatus.OK);
        }else{
            return new ResponseEntity<ChatDto>(HttpStatus.FORBIDDEN);
        }
    }

    // TODO longPooling
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
