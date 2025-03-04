package vermeg.springAI.AIAgent.controller;
import org.springframework.web.bind.annotation.*;
import vermeg.springAI.AIAgent.service.JiraClientService;

@RestController
@RequestMapping("/jira")
public class JiraClientController {
    private final JiraClientService jiraClientService;

    public JiraClientController(JiraClientService jiraClientService) {
        this.jiraClientService = jiraClientService;
    }

    @GetMapping("/projects")
    public String getProjects() {
        return jiraClientService.getAllProjects();
}

@PostMapping("/create-issue")
public String createIssue(@RequestParam String projectKey,
                              @RequestParam String summary,
                              @RequestParam String description,
                              @RequestParam String issueType) {
        return jiraClientService.createIssue(projectKey, summary, description, issueType);
    }
}
