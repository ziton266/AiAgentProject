package vermeg.springAI.AIAgent.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/ai")
public class AiController {
    private final OllamaChatModel  chatModel;
    private static final Logger logger =  LogManager.getLogger(AiController.class);

    public AiController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
        logger.info("OllamaChatModel initialized with model: {}", chatModel);

    }

    @GetMapping("/chat")
    public Map<String,String> generate(@RequestParam(value = "message") String message){
        logger.info("Received message: {}", message);

        return  Map.of("generation",this.chatModel.call(message));
    }
}
