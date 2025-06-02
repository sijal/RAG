package dev.suman.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class ChatController {
    public final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, PgVectorStore pgVectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(pgVectorStore))
                .build();
    }

    @GetMapping("/")
    public String chat() {
        return chatClient.prompt()
                .user("How did the federal reserve's recent interest rate cut impact asset classes according to the analysis")
                .call()
                .content();
    }
}
