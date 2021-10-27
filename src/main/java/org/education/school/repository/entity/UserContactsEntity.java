package org.education.school.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class UserContactsEntity extends GlobalSeqIdEntity {

    @Column(unique = true)
    private String email;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "telegram_link", unique = true)
    private String telegramLink;
    @Column(name = "linkedin_link", unique = true)
    private String linkedinLink;
    @Column(name = "github_link", unique = true)
    private String githubLink;
    @Column(name = "facebook_link", unique = true)
    private String facebookLink;
    @OneToOne(mappedBy = "contacts")
    private UserEntity owner;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }
}
