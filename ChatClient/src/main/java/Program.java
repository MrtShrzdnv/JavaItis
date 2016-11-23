import service.ClientMessagesService;

/**
 * Created by Marat_2 on 23.11.2016.
 */
public class Program {
    public static void main(String[] args) {
        ClientMessagesService service = new ClientMessagesService();
        service.messagesProcessing();
    }
}
