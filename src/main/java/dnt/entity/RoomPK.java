package dnt.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the room database table.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RoomPK implements Serializable {
    //default serial version paymentId, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "room_num", unique = true, nullable = false, length = 10)
    private String roomNum;

    @Column(name = "hotel_name", insertable = false, updatable = false, unique = true, nullable = false, length = 100)
    private String hotelName;
}