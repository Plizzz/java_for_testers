package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

import java.util.Objects;
import java.util.Set;

import static ru.stqa.pft.rest.TestBase.app;

public class Issue {
    private int id;
    private String subject;
    private String description;
    private String state_name;

    public int getId() {
        return id;
    }

    Issue withId(int id) {
        this.id = id;
        return this;
    }

    String getSubject() {
        return subject;
    }

    Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    String getDescription() {
        return description;
    }

    Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (!Objects.equals(subject, issue.subject)) return false;
        return Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    Set<Issue> get() {
        String json = RestAssured.get(String.format("%s/issues.json?limit=1000", app.getProperty("api.url"))).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    int create(Issue newIssue) {
        String json = RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post(String.format("%s/issues.json", app.getProperty("api.url"))).asString();

        JsonElement parsed = new JsonParser().parse(json);

        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    String getIssueWithId(int issueId) {
        String json = RestAssured.get(String.format("%s/issues/%s.json", app.getProperty("api.url"), issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json).getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(parsed, new TypeToken<Set<Issue>>() {
        }.getType());
        return issue.iterator().next().getState_name();
    }
}
