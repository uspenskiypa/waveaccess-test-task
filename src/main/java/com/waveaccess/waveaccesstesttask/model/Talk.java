package com.waveaccess.waveaccesstesttask.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "talks")
public class Talk extends AbstractNamedEntity {

    public Talk() {
    }

    public Talk(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Talk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
