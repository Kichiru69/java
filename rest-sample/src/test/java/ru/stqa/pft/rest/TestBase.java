package ru.stqa.pft.rest;

import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {
    String status = RestTests.getIssueStatus(issueId);
    return !status.equals("Closed");
  }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
