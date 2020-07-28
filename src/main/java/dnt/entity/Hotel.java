package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the hotel database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hotel")
@NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h where delete_flag = 'N'")
public class Hotel extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "hotel_name", unique = true, nullable = false, length = 100)
    private String hotelName;

    @Column(name = "apartment_num", nullable = false, length = 100)
    private String apartmentNum;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 300)
    private String descr;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String provincial;

    @Column(nullable = false, length = 100)
    private String street;

    private int reservationHours = 4;

    //bi-directional many-to-one association to Report
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private Set<Report> reports;

    //bi-directional many-to-one association to Room
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    //bi-directional many-to-one association to Room
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Room> staffs;

    public Report addReport(Report report) {
        getReports().add(report);
        report.setHotel(this);

        return report;
    }

    public Report removeReport(Report report) {
        getReports().remove(report);
        report.setHotel(null);

        return report;
    }

    public Room addRoom(Room room) {
        getRooms().add(room);
        room.setHotel(this);

        return room;
    }

    public Room removeRoom(Room room) {
        getRooms().remove(room);
        room.setHotel(null);

        return room;
    }

}