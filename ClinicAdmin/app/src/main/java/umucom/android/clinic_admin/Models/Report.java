package umucom.android.clinic_admin.Models;

public class Report {

    private int ID;
    private int Report_P_ID;
    private String Path;
    private String Comments;
    private String Test;
    private String TestDate;

    public Report(String path, String comments, String test, String testDate) {
        Path = path;
        Comments = comments;
        Test = test;
        TestDate = testDate;
    }

    public Report(int report_P_ID, String path, String comments, String test, String testDate) {
        Report_P_ID = report_P_ID;
        Path = path;
        Comments = comments;
        Test = test;
        TestDate = testDate;
    }
    public Report(){}

    public Report(int ID, int report_P_ID, String path, String comments, String test, String testDate) {
        this.ID = ID;
        Report_P_ID = report_P_ID;
        Path = path;
        Comments = comments;
        Test = test;
        TestDate = testDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getReport_P_ID() {
        return Report_P_ID;
    }

    public void setReport_P_ID(int report_P_ID) {
        Report_P_ID = report_P_ID;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        Test = test;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String testDate) {
        TestDate = testDate;
    }
}
