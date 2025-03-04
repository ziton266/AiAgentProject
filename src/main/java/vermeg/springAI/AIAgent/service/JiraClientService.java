package vermeg.springAI.AIAgent.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Base64;

@Service
public class JiraClientService {
    private final WebClient webClient;

    public JiraClientService(@Value("${jira.url}") String jiraUrl,
                             @Value("${jira.username}") String username,
                             @Value("${jira.password}") String password) {
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        this.webClient = WebClient.builder()
                .baseUrl(jiraUrl + "/rest/api/2")
                .defaultHeader("Authorization", "Basic " + encodedAuth)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    //  Récupérer la liste des projets
    public String getAllProjects() {
        return webClient.get()
                .uri("/project")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    //  Créer un ticket JIRA
    public String createIssue(String projectKey, String summary, String description, String issueType) {
        String body = String.format("""
                {
                    "fields": {
                        "project": { "key": "%s" },
                        "summary": "%s",
                        "description": "%s",
                        "issuetype": { "name": "%s" }
                    }
                }
                """, projectKey, summary, description, issueType);

        return webClient.post()
                .uri("/issue")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

