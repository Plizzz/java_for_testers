package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {
    @Test
    public void testCreateIssue() {
        skipIfNotFixed(1528);
        Set<Issue> oldIssues = app.issue().get();
        Issue newIssue = app.issue().withSubject("This is Alex's subject").withDescription("Hello world");

        int issueId = app.issue().create(newIssue);
        Set<Issue> newIssues = app.issue().get();
        oldIssues.add(newIssue.withId(issueId));

        assertEquals(newIssues, oldIssues);
    }
}
