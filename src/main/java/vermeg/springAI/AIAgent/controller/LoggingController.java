package vermeg.springAI.AIAgent.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LoggingController {

    private static final Logger logger = LogManager.getLogger(LoggingController.class);
    @GetMapping
    public String testLogging() {
        logger.info("Info: This is a test log message.");
        logger.debug("Debug: This is a debug log message.");
        logger.error("Error: This is an error log message.");
        return "helllo world!";
    }


}
