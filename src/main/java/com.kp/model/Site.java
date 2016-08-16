package com.kp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by user on 8/13/16.
 */

@Entity
@Table(name = "site")
public class Site {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    public String site_name;

    @Column
    public String description;


}
