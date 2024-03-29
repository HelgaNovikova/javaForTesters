package ru.stqa.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.mantis.model.Issue;
import ru.stqa.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) ->
                new Project().setId(p.getId().intValue()).setName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator()
                //        .getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.4/mantisbt-2.25.4/api/soap/mantisconnect.php"));
                .getMantisConnectPort(new URL(app.getProperty("web.mantisConnectPortType")));
        return mc;
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().setId(createdIssueData.getId().intValue()).setSummary(createdIssueData.getSummary())
                .setDescription(createdIssueData.getDescription())
                .setProject(new Project().setId(createdIssueData.getProject().getId().intValue())
                        .setName(createdIssueData.getProject().getName()));
    }

    public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        String status = issueData.getStatus().getName();
        return status;
    }
}
