package dnt.entity.Audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDt", "updatedDt"}, allowGetters = true)
@Setter
public abstract class AuditDate implements Serializable {

    @Column(name = "created_dt", nullable = false, updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedDt;

    @Getter
    @Column(name = "delete_flag", nullable = false, columnDefinition = "varchar(3) default 'N'")
    private String deleteFlag;

    public String getCreatedDt() {
        return (new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")).format(createdDt);
    }

    public String getUpdatedDt() {
        return (new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")).format(updatedDt);
    }
}
