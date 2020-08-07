package dnt.entity.Data;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountRequest {

	@NotBlank
    private String username;

	@NotBlank
    private String password;

	@NotBlank
    private String staffId;
	
	@NotBlank
	private String roleId;
}
