package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the payment database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment")
@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p where delete_flag = 'N'")
public class Payment extends AuditObject {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", unique = true, nullable = false)
    private int paymentId;

    @Column(nullable = false)
    private double debit;

    @Column(nullable = false)
    private double paid;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "total_price_room", nullable = false)
    private double totalPriceRoom;

    @Column(name = "total_price_service", nullable = false)
    private double totalPriceService;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    //bi-directional many-to-one association to Guest
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_phone", nullable = false)
    private Guest guest;

    //bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    public Payment paid(boolean fullPaid) {
        this.setPaid(fullPaid ? totalPrice : 0);
        this.setDebit(totalPrice - paid);
        return this;
    }
}