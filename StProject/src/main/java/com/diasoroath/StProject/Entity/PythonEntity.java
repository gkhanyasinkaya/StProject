package com.diasoroath.StProject.Entity;

public class PythonEntity {

    private String base64Photo;
    private String userID;
    private String reportID;

    public PythonEntity() {}

    public PythonEntity(String base64Photo, String userID, String reportID) {
        this.base64Photo = base64Photo;
        this.userID = userID;
        this.reportID = reportID;
    }


    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }
}
