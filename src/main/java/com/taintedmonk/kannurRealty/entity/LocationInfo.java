package com.taintedmonk.kannurRealty.entity;

import javax.persistence.*;

@Entity
@Table(name="locationinfo")
public class LocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "heading")
    private String heading;

    @Column(name = "description")
    private String description;

    @Column(name = "fileid")
    private String fileid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
}
