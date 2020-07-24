package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditDate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the booking database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "booking")
@NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b where delete_flag = 'N'")
public class Booking extends AuditDate {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", unique = true, nullable = false)
    private int bookingId;

    @Column(nullable = false)
    private LocalDate arrive;

    @Column(nullable = false)
    private LocalDate depart;

    //bi-directional many-to-one association to Guest
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone", nullable = false)
    private Guest guest;

    //bi-directional many-to-one association to Staff
    @JsonIgnore
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    //bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "booking", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Payment payment = new Payment();

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BookingDetail> bookingDetails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GuestService> guestServices = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_name", nullable = false)
    private Hotel hotel;

    public void addBookingDetail(BookingDetail bookingDetail) {
        getBookingDetails().add(bookingDetail);
        bookingDetail.setBooking(this);
    }

    public void removeDetailBooking(BookingDetail bookingDetail) {
        bookingDetail.setBooking(null);
        getBookingDetails().remove(bookingDetail);
    }

    public void addGuestService(GuestService guestService) {
        this.getGuestServices().add(guestService);
        guestService.setBooking(this);
    }

    public void removeGuestService(GuestService guestService) {
        guestService.setBooking(null);
        this.getGuestServices().remove(guestService);
    }

    public Payment createPayment() {
        double roomPrice = getBookingDetails().stream()
                .map(d -> d.getRoom().getRoomType().getPrice()).reduce(Double::sum).orElse(0.0);
        double servicePrice = getGuestServices().stream()
                .map(d -> d.getService().getPrice() * d.getAmountOfService()).reduce(Double::sum).orElse(0.0);
        payment.setTotalPriceRoom(roomPrice);
        payment.setTotalPriceService(servicePrice);
        payment.setTotalPrice(roomPrice + servicePrice);
        payment.setBooking(this);
        return payment;
    }
}