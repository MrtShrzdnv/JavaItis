package service;

import model.MessageDto;
import util.MyRestClient;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Marat_2 on 23.11.2016.
 */
public class ClientMessagesService {
    private ExecutorService executorService;
    private MyRestClient myRestClient;
    private String username;
    public ClientMessagesService() {
        executorService = Executors.newFixedThreadPool(1);
        myRestClient = new MyRestClient();
        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
    }
    private void messagesHandle() {
        Runnable handleMessagesTask = () -> {
            while(true) {
                MessageDto[] messages = myRestClient.getMessages();
                for (MessageDto currentMessage : messages) {
                    System.out.println(currentMessage.getText());
                }
            }
        };
        executorService.submit(handleMessagesTask);
    }
    public void messagesProcessing() {
        messagesHandle();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String text = scanner.nextLine();
            MessageDto messageDto = new MessageDto(username, text);
            myRestClient.sendMessage(messageDto);
        }
    }
}

