import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Message {
    private final String content;
    private final String sender;
    private final String recipient;

    public Message(String content, String sender, String recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String toSummaryString() {
        return "Content: " + content + "\n" +
                "Sender: " + sender + "\n" +
                "Recipient: " + recipient;
    }

    public String toDetailedString() {
        return toSummaryString() + "\n" +
                "Content Length: " + content.length() + "\n" +
                "Sender Uppercase: " + sender.toUpperCase() + "\n" +
                "Recipient Lowercase: " + recipient.toLowerCase();
    }
}

class MessagingService {
    private final Map<String, List<Message>> inbox = new HashMap<>();

    public void sendMessage(String content, String sender, String recipient) {
        Message message = new Message(content, sender, recipient);
        inbox.computeIfAbsent(recipient, key -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessagesForRecipient(String recipient) {
        return inbox.getOrDefault(recipient, new ArrayList<>());
    }

    public String getAllMessagesAsString() {
        StringBuilder sb = new StringBuilder();
        for (var messages : inbox.values()) {
            for (Message message : messages) {
                sb.append(message.toSummaryString()).append("\n");
            }
        }
        return sb.toString().trim();
    }
}

public class Messaging {
    public static void main(String[] args) {
        MessagingService service = new MessagingService();

        service.sendMessage("Hello, tenant!", "Property Manager", "Tenant A");
        service.sendMessage("Rent due next week.", "Property Owner", "Tenant A");
        service.sendMessage("Maintenance request.", "Tenant A", "Property Manager");

        List<Message> messages = service.getMessagesForRecipient("Tenant A");
        for (Message msg : messages) {
            System.out.println("[Detail]");
            System.out.println(msg.toDetailedString());
        }

        System.out.println("==== All Messages ====");
        System.out.println(service.getAllMessagesAsString());
    }
}