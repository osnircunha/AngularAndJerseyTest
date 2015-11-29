package com.ocunha.election.object;

import javax.persistence.*;

/**
 * Created by osnircunha on 9/8/15.
 */
@Entity
@Table(name = "tb_position")
public class Position {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
