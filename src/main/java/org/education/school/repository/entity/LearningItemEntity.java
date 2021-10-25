package org.education.school.repository.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LearningItemEntity extends GlobalSeqIdEntity {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
