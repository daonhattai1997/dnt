package dnt.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the report database table.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReportPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "report_id", unique = true, nullable = false)
    private int reportId;

    @Column(name = "hotel_name", insertable = false, updatable = false, unique = true, nullable = false, length = 100)
    private String hotelName;
}