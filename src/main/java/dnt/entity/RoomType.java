package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the room_type database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room_type")
@NamedQuery(name = "RoomType.findAll", query = "SELECT r FROM RoomType r where delete_flag = 'N'")
public class RoomType extends AuditDate {
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