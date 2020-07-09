package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the service_type database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "service_type")
@NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s where delete_flag = 'N'")
public class ServiceType extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_service", unique = true, nullable = false)
    private int idTypeService;

    @Column(nullable = false, length = 1000)
    private String descr;

    @Column(name = "type_name", nullable = false, length = 100)
    private String typeName;

    //bi-directional many-to-one association to Service
    @OneToMany(mappedBy = "serviceType")
    private Set<Service> services;

    public Service addService(Service service) {
        getServices().add(service);
        service.setServiceType(this);

        return service;
    }

    public Service removeService(Service service) {
        getServices().remove(service);
        service.setServiceType(null);

        return service;
    }

}