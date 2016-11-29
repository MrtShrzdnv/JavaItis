package converter;

import dto.ChatDto;
import dto.MessageDto;
import dto.UserDto;
import model.Chat;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 29.11.2016.
 */
public class ConverterDto {
    @Autowired
    private UserService userService;

    public Chat convert(ChatDto chatDto){
        List<User> usersList = new ArrayList<>();
        for(UserDto userDto : chatDto.getUsersList()){
            usersList.add(convert(userDto));
        }
        Chat chat = Chat.newBuilder().setId(chatDto.getId()).setName(chatDto.getName()).setUsersList(usersList).build();
        return chat;
    }

    public ChatDto convert(Chat chat){
        List<UserDto> usersDtoList = new ArrayList<>();
        for(User user : chat.getUsersList()){
            usersDtoList.add(convert(user));
        }
        return new ChatDto(chat.getId(), chat.getName(), usersDtoList);
    }

    public User convert(UserDto userDto){
        User user = User.newBuilder().setId(userDto.getId()).setName(userDto.getName()).build();
        return user;
    }

    public UserDto convert(User user){
        return new UserDto(user.getId(), user.getName());
    }

    public Message convert(MessageDto messageDto){
        Message message = Message.newBuilder().setId(messageDto.getId()).setText(messageDto.getText()).build();
        return message;
    }

    public MessageDto convert(Message message){
        String userName = userService.findById(message.getUserId()).getName();
        return new MessageDto(message.getText(), userName);
    }
}
