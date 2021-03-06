package com.waveaccess.waveaccesstesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room extends AbstractNamedEntity {

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    public Room() {
    }

    public Room(Integer id, String name) {
        super(id, name);
    }

    public Room(Room room) {
        this(room.getId(), room.getName());
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
