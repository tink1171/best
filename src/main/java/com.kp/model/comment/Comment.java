package com.kp.model.comment;

import com.kp.model.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alex on 5/5/2016.
 */
@Entity
@Table(name = "comment")
public class Comment implements BaseEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_avatar_url")
    private String userAvatarUrl;

    @Column(name = "creation_time", nullable = false)
    private String creationTime;


    @PrePersist
    public void prePersist() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationTime = sdf.format(dt);
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

//    @Column(name = "creation_time", nullable = false)
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    private DateTime creationTime;

//    @PrePersist
//    public void prePersist() {
//        DateTime now = DateTime.now();
//        this.creationTime = now;
//    }

//    public DateTime getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(DateTime creationTime) {
//        this.creationTime = creationTime;
//    }

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

}
