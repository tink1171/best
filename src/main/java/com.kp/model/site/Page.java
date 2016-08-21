package com.kp.model.site;


import javax.persistence.*;

@Entity
//@Table(name = "page")
public class Page {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "content",length = 10000)
    private String content;

    @Column(name = "content")
    private String tempalate;

    public Page(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
