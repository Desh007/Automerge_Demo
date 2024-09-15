import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/web/bitbucket")
public class WebBitbucketController {

    private final BitbucketService bitbucketService;

    public WebBitbucketController(BitbucketService bitbucketService) {
        this.bitbucketService = bitbucketService;
    }

    @GetMapping("/commits")
    public String getCommits(Model model) {
        JsonNode commits = bitbucketService.getCommits();
        model.addAttribute("commits", commits);
        return "commits";
    }

    // Similar methods for pull requests
}
