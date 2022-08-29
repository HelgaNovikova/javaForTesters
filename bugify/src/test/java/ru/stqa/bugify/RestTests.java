package ru.stqa.bugify;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(2203);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().setSubject("test subject").setDescription("test description");
        int issueId = createIssue(newIssue);
        oldIssues.add(newIssue.setId(issueId));
        Set<Issue> newIssues = getIssues();
        Assert.assertEquals(oldIssues, newIssues);
    }

    private void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement jsonParsed = new JsonParser().parse(json);
        JsonElement issues = jsonParsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        if (issue.iterator().next().getState() != 3){
            return true;
        };
        return false;
    }


    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement jsonParsed = new JsonParser().parse(json);
        int issueId = jsonParsed.getAsJsonObject().get("issue_id").getAsInt();
        return issueId;
    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement jsonParsed = new JsonParser().parse(json);
        JsonElement issues = jsonParsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
