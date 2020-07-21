package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the guest database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "guest")
@NamedQuery(name = "Guest.findAll", query = "SELECT g FROM Guest g where delete_flag = 'N'")
public class Guest extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "guest_phone", unique = true, nullable = false, length = 50)
    private String guestPhone;

    @Column(nullable = false, length = 200)
    private String address;

    @JsonIgnore
    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 300)
    private String email;

    @Column(nullable = false, length = 3)
    private String gender;

    @JsonIgnore
    @Column(name = "id_card", nullable = false, precision = 10)
    private long idCard;

    @Column(nullable = false, length = 50)
    private String name;

    //bi-directional many-to-one association to Booking
    @JsonIgnore
    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings = new ArrayList<>();

    //bi-directional many-to-one association to Payment
    @JsonIgnore
    @OneToMany(mappedBy = "guest")
    private List<Payment> payments = new ArrayList<>();

    public Booking addBooking(Booking booking) {
        getBookings().add(booking);
        booking.setGuest(this);
        return booking;
    }

    public Booking removeBooking(Booking booking) {
        getBookings().remove(booking);
        booking.setGuest(null);

        return booking;
    }

    public Payment addPayment(Payment payment) {
        getPayments().add(payment);
        payment.setGuest(this);

        return payment;
    }

    public Payment removePayment(Payment payment) {
        getPayments().remove(payment);
        payment.setGuest(null);

        return payment;
    }

}