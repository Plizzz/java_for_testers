package ru.stqa.pft.rest;

import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Arrays;

public class TestBase {
    static final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void init() throws IOException {
        app.init();
        RestAssured.authentication = RestAssured.basic(app.getProperty("RA.key"), "");
    }

    void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        return !Arrays.asList("Closed", "Resolved").contains(app.issue().getIssueWithId(issueId));
    }
}
