package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the manager database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "staff")
@NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s where delete_flag = 'N'")
public class Staff extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", unique = true, nullable = false)
    private int staffId;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 3)
    private String gender;

    @Column(nullable = false, length = 100)
    private String name;

    //bi-directional many-to-one association to Group
    @OneToMany(mappedBy = "manager")
    private Set<Group> ownGroups;

    //bi-directional many-to-many association to Group
    @ManyToMany(mappedBy = "staffs")
    private Set<Group> ownedByGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_name")
    private Hotel hotel;

    //bi-directional many-to-one association to User
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "staff")
    private User account;

    //bi-directional many-to-one association to Booking
    @OneToMany(mappedBy = "staff")
    private Set<Booking> bookings;

    //bi-directional many-to-one association to GuestService
    @OneToMany(mappedBy = "staff")
    private Set<GuestService> guestServices;

    //bi-directional many-to-one association to Report
    @OneToMany(mappedBy = "staff")
    private Set<Report> reports;

    //bi-directional many-to-many association to Role
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "role_staff",
            joinColumns = {
                    @JoinColumn(name = "role_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "staff_id", nullable = false)
            })
    private Set<Role> roles = new HashSet<>();

    //bi-directional many-to-one association to Service
    @OneToMany(mappedBy = "staff")
    private Set<Service> services;

    public void addRole(Role role) {
        getRoles().add(role);
        role.getStaffs().add(this);
    }

    public Group addOwn(Group group) {
        getOwnGroups().add(group);
        group.setManager(this);
        return group;
    }

    public Group removeOwn(Group group) {
        getOwnGroups().remove(group);
        group.setManager(null);
        return group;
    }

    public Group addOwnedBy(Group group) {
        getOwnedByGroups().add(group);
        group.getStaffs().add(this);
        return group;
    }

    public Group removeOwnedBy(Group group) {
        getOwnedByGroups().remove(group);
        group.getStaffs().remove(this);
        return group;
    }

    public Booking addBooking(Booking booking) {
        getBookings().add(booking);
        booking.setStaff(this);
        return booking;
    }

    public Booking removeBooking(Booking booking) {
        getBookings().remove(booking);
        booking.setStaff(null);
        return booking;
    }

    public GuestService addGuestService(GuestService guestService) {
        getGuestServices().add(guestService);
        guestService.setStaff(this);

        return guestService;
    }

    public GuestService removeGuestService(GuestService guestService) {
        getGuestServices().remove(guestService);
        guestService.setStaff(null);
        return guestService;
    }

    public Report addReport(Report report) {
        getReports().add(report);
        report.setStaff(this);
        return report;
    }

    public Report removeReport(Report report) {
        getReports().remove(report);
        report.setStaff(null);
        return report;
    }

    public Service addService(Service service) {
        getServices().add(service);
        service.setStaff(this);
        return service;
    }

    public Service removeService(Service service) {
        getServices().remove(service);
        service.setStaff(null);
        return service;
    }

}