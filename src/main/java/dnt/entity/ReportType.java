package dnt.entity;

import dnt.entity.Audit.AuditObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the report_type database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "report_type")
@NamedQuery(name = "ReportType.findAll", query = "SELECT r FROM ReportType r where delete_flag = 'N'")
public class ReportType extends AuditObject {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_type_id", unique = true, nullable = false)
    private int reportTypeId;

    @Column(name = "report_type_name", nullable = false, length = 100)
    private String reportTypeName;

    //bi-directional many-to-one association to Report
    @OneToMany(mappedBy = "reportType")
    private Set<Report> reports;

    public Report addReport(Report report) {
        getReports().add(report);
        report.setReportType(this);

        return report;
    }

    public Report removeReport(Report report) {
        getReports().remove(report);
        report.setReportType(null);

        return report;
    }

}