package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class consultaChatGPT {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

    public static String obterTraducao(String texto) {
        String apiKey = DOTENV.get("OPENAI_APIKEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Variável OPENAI_APIKEY não definida");
        }

        OpenAiService service = new OpenAiService(apiKey);

        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-4o-mini")
                .messages(List.of(new ChatMessage(ChatMessageRole.USER.value(),
                        "traduza para o português o texto: " + texto)))
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createChatCompletion(requisicao);
        return resposta.getChoices().get(0).getMessage().getContent();
    }
}
