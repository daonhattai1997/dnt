package dnt.entity.Data;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class StaffManagerResponse {
	
	private String accountName;
	
	private String role;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(locale = "vi_VN", pattern = "dd-MM-yyyy : HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date createdDt;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(locale = "vi_VN", pattern = "dd-MM-yyyy : HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedDt;
	
	private String createdBy;
	
	private String updatedBy;
	
	
	
	
}
