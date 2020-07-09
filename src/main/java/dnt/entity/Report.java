package dnt.entity;

import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "report")
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r where delete_flag = 'N'")
public class Report extends AuditObject {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ReportPK id;

    @Column(nullable = false, length = 100)
    private String content;

    //bi-directional many-to-one association to Hotel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_name", nullable = false, insertable = false, updatable = false)
    private Hotel hotel;

    //bi-directional many-to-one association to ReportType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_type_id", nullable = false)
    private ReportType reportType;

    //bi-directional many-to-one association to Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by_staff", nullable = false)
    private Staff staff;

}