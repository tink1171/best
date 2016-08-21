package com.kp.model.site;//package com.kp.model.site;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.websystique.springmvc.model.BaseEntity;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * Created by alex on 5/4/2016.
// */
//
//@Entity
//@Table(name = "tag")
//public class Tag implements BaseEntity {
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue
//    private long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy="tags",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//    List<Comix> comics;
//
//
//
//    public Tag() {
//    }
//
//    public Tag(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public List<Comix> getComics() {
//        return comics;
//    }
//
//    public void setComics(List<Comix> comics) {
//        this.comics = comics;
//    }
//
//}
