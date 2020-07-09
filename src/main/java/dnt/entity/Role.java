package dnt.entity;

import dnt.entity.Audit.AuditObject;
import dnt.entity.EnumType.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the role database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r where delete_flag = 'N'")
public class Role extends AuditObject {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private int roleId;

    private String description;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoleName name;

    //bi-directional many-to-many association to Staff
    @ManyToMany(mappedBy = "roles")
    private Set<Staff> staffs = new HashSet<>();

}

