package dnt.entity.Data;


import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import dnt.entity.Role;
import dnt.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
	
	private String username;
	private Staff staff;
	private List<Role> roles;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(locale = "vi_VN", pattern = "dd-MM-yyyy : HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date createdDt;

	public AccountInfo(String username, Staff staff, Date createdDt) {
		super();
		this.username = username;
		this.staff = staff;
		this.createdDt = createdDt;
	}

	
}
