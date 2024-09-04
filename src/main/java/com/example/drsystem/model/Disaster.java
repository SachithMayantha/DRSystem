package com.example.drsystem.model;

import java.time.LocalDate;

public class Disaster {

    private int disasterId;
    private String type;
    private String location;
    private String locationType;
    private String description;

    private String severity;

    private LocalDate date;

    private String reportedBy;

    public Disaster(int disasterId, String type, String location, String locationType, String description, String severity, LocalDate date, String reportedBy) {
        this.disasterId = disasterId;
        this.type = type;
        this.location = location;
        this.locationType = locationType;
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

    public String getLocationType() {
        return locationType;
    }
    public void setLocationType(String locationType) {
        this.locationType = locationType;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }
}
