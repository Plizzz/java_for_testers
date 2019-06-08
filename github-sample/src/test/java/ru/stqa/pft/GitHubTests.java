package ru.stqa.pft;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("c1360a3355776e9d0cd957812ffe7009d2c46db1");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("Plizzz", "java_for_testers")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }

    }
}
