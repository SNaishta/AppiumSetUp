package utilities.Integration;

import com.offbytwo.jenkins.JenkinsServer;

import java.net.URI;
import java.net.URISyntaxException;

public class Jenkins {
    JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8080/jenkins"), "admin", "password");

    public Jenkins() throws URISyntaxException {
    }
}
