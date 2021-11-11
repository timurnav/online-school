package org.education.school.service.dto;

import java.util.Date;

public class HomeWork {
    public final int id;
    public final String title;
    public final String description;
    public final int attempts;
    public final Date till;

    public HomeWork(int id, String title, String description, int attempts, Date till) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.attempts = attempts;
        this.till = till;
    }
}
