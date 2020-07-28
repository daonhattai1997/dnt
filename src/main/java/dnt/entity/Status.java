package dnt.entity;

import dnt.entity.EnumType.StatusName;
import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


/**
 * The persistent class for the status database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "status")
@NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s where delete_flag = 'N'")
public class Status extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", unique = true, nullable = false)
    private int statusId;

    @NaturalId
    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusName statusName;

    public Status(StatusName name) {
        this.statusName = name;
    }
}

