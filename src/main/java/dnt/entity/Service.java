package dnt.entity;

import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the service database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "service")
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s where delete_flag = 'N'")
public class Service extends AuditObject {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", unique = true, nullable = false)
    private int idService;

    @Column(nullable = false, length = 1000)
    private String descr;

    @Column(nullable = false)
    private double price;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    //bi-directional many-to-one association to GuestService
    @OneToMany(mappedBy = "service")
    private Set<GuestService> guestServices;

    //bi-directional many-to-one association to ServiceType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_service", nullable = false)
    private ServiceType serviceType;

    //bi-directional many-to-one association to Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public GuestService addGuestService(GuestService guestService) {
        getGuestServices().add(guestService);
        guestService.setService(this);
        return guestService;
    }

    public GuestService removeGuestService(GuestService guestService) {
        getGuestServices().remove(guestService);
        guestService.setService(null);
        return guestService;
    }

}