import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class BitbucketService {

    @Value("${bitbucket.api.url}")
    private String bitbucketApiUrl;

    @Value("${bitbucket.api.token}")
    private String bitbucketApiToken;

    @Value("${bitbucket.workspace}")
    private String workspace;

    @Value("${bitbucket.repo.slug}")
    private String repoSlug;

    private final RestTemplate restTemplate;

    public BitbucketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bitbucketApiToken);
        return headers;
    }

    public JsonNode getCommits() {
        String url = String.format("%s/repositories/%s/%s/commits", bitbucketApiUrl, workspace, repoSlug);
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
        return response.getBody();
    }

    public JsonNode getOpenPullRequests() {
        String url = String.format("%s/repositories/%s/%s/pullrequests?state=OPEN", bitbucketApiUrl, workspace, repoSlug);
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
        return response.getBody();
    }

    public JsonNode getMergedPullRequests() {
        String url = String.format("%s/repositories/%s/%s/pullrequests?state=MERGED", bitbucketApiUrl, workspace, repoSlug);
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
        return response.getBody();
    }
}
