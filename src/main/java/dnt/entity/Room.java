package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")
@NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id", unique = true, nullable = false)
    private int roomId;

    @Column(name = "floor_num", nullable = false)
    private int floorNum;

    //bi-directional many-to-one association to RoomType
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private RoomType roomType;

}