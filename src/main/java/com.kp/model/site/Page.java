package com.kp.model.site;


import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "content1",length = 10000)
    private String content1;

    @Column(name = "content2",length = 10000)
    private String content2;

    @Column(name = "content3",length = 10000)
    private String content3;

    @Column(name = "title",length = 40)
    private String title;

    @Column(name = "template")
    private int template;

    public Page(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                ", content3='" + content3 + '\'' +
                ", title='" + title + '\'' +
                ", template=" + template +
                '}';
    }
}
