import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/bitbucket")
public class BitbucketController {

    private final BitbucketService bitbucketService;

    public BitbucketController(BitbucketService bitbucketService) {
        this.bitbucketService = bitbucketService;
    }

    @GetMapping("/commits")
    public JsonNode getCommits() {
        return bitbucketService.getCommits();
    }

    @GetMapping("/pullrequests/open")
    public JsonNode getOpenPullRequests() {
        return bitbucketService.getOpenPullRequests();
    }

    @GetMapping("/pullrequests/merged")
    public JsonNode getMergedPullRequests() {
        return bitbucketService.getMergedPullRequests();
    }
}
