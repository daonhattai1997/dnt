package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dnt.entity.Audit.AuditDate;
import dnt.entity.EnumType.RoleName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the role database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value= {"staffs", "menus"})
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r where delete_flag = 'N'")
public class Role extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private int roleId;

    @Column(name = "description")
    private String description;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoleName name;

    //bi-directional many-to-many association to Staff
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @JsonIgnore
    private List<Staff> staffs = new ArrayList<>();

    //bi-directional many-to-many association to Staff
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<MenuProgram> menus = new ArrayList<>();

}

