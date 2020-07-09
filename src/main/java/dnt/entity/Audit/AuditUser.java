package dnt.entity.Audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
@Getter
@Setter
public abstract class AuditUser extends AuditDate implements Serializable {

    @Column(name = "create_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createdBy;

    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private Integer updatedBy;

}
