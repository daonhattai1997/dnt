package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")
@NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r where delete_flag = 'N'")
public class Room extends AuditObject {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoomPK id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_name", nullable = false, insertable = false, updatable = false)
    private Hotel hotel;

    @Column(name = "floor_num", nullable = false)
    private int floorNum;

    //bi-directional many-to-one association to RoomType
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private RoomType roomType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}