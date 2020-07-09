package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the detail_booking database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "booking_detail")
@NamedQuery(name = "BookingDetail.findAll", query = "SELECT d FROM BookingDetail d where delete_flag = 'N'")
public class BookingDetail extends AuditObject {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_detail_id", unique = true, nullable = false)
    private int bookingDetailId;

    @Column(name = "adult_amount", nullable = false)
    private int adultAmount;

    @Column(name = "children_amount", nullable = false)
    private int childrenAmount;

    //bi-directional many-to-one association to Booking
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    //bi-directional many-to-one association to Room
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "hotel_name", referencedColumnName = "hotel_name"),
            @JoinColumn(name = "room_num", referencedColumnName = "room_num")
    })
    private Room room;

}