package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the room_type database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room_type")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "RoomType.findAll", query = "SELECT r FROM RoomType r")
public class RoomType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", unique = true, nullable = false)
    private int typeId;

    @Column(nullable = false, length = 1000)
    private String descr;

    @Column(nullable = false, length = 100)
    private String image;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private double price;

    //bi-directional many-to-one association to Room
    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedDt;

    public Room addRoom(Room room) {
        getRooms().add(room);
        room.setRoomType(this);

        return room;
    }

    public Room removeRoom(Room room) {
        getRooms().remove(room);
        room.setRoomType(null);

        return room;
    }

}