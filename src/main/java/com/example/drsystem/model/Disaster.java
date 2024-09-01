package com.example.drsystem.model;

import java.util.Date;

public class Disaster {

    private int disasterId;
    private String type;
    private String location;

    private String description;

    private String severity;

    private Date date;

    private String reportedBy;

    public Disaster(int disasterId, String type, String location, String description, String severity, Date date, String reportedBy) {
        this.disasterId = disasterId;
        this.type = type;
        this.location = location;
        this.description = description;
        this.severity = severity;
        this.date = date;
        this.reportedBy = reportedBy;
    }

    public int getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(int disasterId) {
        this.disasterId = disasterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }
}
