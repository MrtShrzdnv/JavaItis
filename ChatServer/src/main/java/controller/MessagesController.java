package controller;

import model.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.MessagesService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 23.11.2016.
 */
@RestController
public class MessagesController {
    @Autowired
    MessagesService service;
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public List<MessageDto> getMessages(){
        synchronized (service.getNewMessages()) {
            while (service.getNewMessages().isEmpty()) {
                try {
                    service.getNewMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException();
                }
            }
            List<MessageDto> result = new ArrayList<>(service.getNewMessages());
            service.getNewMessages().clear();
            return result;
        }
    }
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void sendMessage(MessageDto message){
        service.hadleMessages(message);
    }

}
