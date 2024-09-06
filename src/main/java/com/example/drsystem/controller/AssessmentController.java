package com.example.drsystem.controller;

import com.example.drsystem.model.Disaster;

public class AssessmentController {

    public int assessDisaster(Disaster disaster) {
        int priority = 0;

        // Assess severity
        switch (disaster.getSeverity()) {
            case "Critical":
                priority += 5;
                break;
            case "Severe":
                priority += 4;
                break;
            case "High":
                priority += 3;
                break;
            case "Moderate":
                priority += 2;
                break;
            case "Low":
                priority += 1;
                break;
        }

        // Assess location - for example, a disaster in a densely populated area might be given a higher priority
        if (disaster.getLocation().equals("Urban Area")) {
            priority += 3;
        } else if (disaster.getLocation().equals("Industrial Zone")) {
            priority += 4;
        } else {
            priority += 2;
        }

        // Assess the type of disaster
        switch (disaster.getType()) {
            case "Fire":
                priority += 4;
                break;
            case "Flood":
                priority += 3;
                break;
            case "Earthquake":
                priority += 5;
                break;
            case "Tornado":
                priority += 4;
                break;
            case "Hurricane":
                priority += 5;
                break;
        }

        return priority; // Higher priority numbers indicate more urgent disasters
    }

}
