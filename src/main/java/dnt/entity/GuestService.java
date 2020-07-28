package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * The persistent class for the guest_service database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "guest_service")
@NamedQuery(name = "GuestService.findAll", query = "SELECT g FROM GuestService g where delete_flag = 'N'")
public class GuestService extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestServiceId;

    @Column(nullable = false)
    private int amountOfService;

    //bi-directional many-to-one association to Payment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    //bi-directional many-to-one association to Service
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false)
    private Service service;

    //bi-directional many-to-one association to Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

}