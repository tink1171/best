package com.kp.model.site;

import com.kp.model.comment.Comment;

import javax.persistence.*;
import java.util.List;

/**
 * Created by user on 8/13/16.
 */

@Entity
//@Table(name = "site")
public class Site {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name="username")
    private String username;


    @Column(name = "site_name")
    private String sitename;

//    @Column(name = "rate")
//    private double rate;

    @Column(name="site_logo_url")
    private String siteLogoUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_time", nullable = false)
    private String creationTime;

//    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//    @JoinTable(name="page",
//            joinColumns={@JoinColumn(name="site_id")},
//            inverseJoinColumns={@JoinColumn(name="page_id")})
//    private List<Page> pages;

//    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//    @JoinTable(name="site_comment",
//            joinColumns={@JoinColumn(name="site_id")},
//            inverseJoinColumns={@JoinColumn(name="comment_id")})
//    private List<Comment> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSiteLogoUrl() {
        return siteLogoUrl;
    }

    public void setSiteLogoUrl(String siteLogoUrl) {
        this.siteLogoUrl = siteLogoUrl;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }
//
//    public double getRate() {
//        return rate;
//    }
//
//    public void setRate(double rate) {
//        this.rate = rate;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", siteLogoUrl='" + siteLogoUrl + '\'' +
                ", sitename='" + sitename + '\'' +
 //               ", rate=" + rate +
                ", description='" + description + '\'' +
  //              ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
