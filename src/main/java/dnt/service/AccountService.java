package dnt.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dnt.entity.User;
import dnt.entity.Data.AccountInfo;
import dnt.entity.Data.AccountRequest;
import dnt.entity.Data.Constants;
import dnt.repository.AccountRepository;
import dnt.repository.RoleRepository;
import dnt.repository.StaffRepository;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class AccountService{
	private AccountRepository accountRepository;
	private StaffRepository staffRepository;
	private RoleRepository roleRepository;
	
	public List<User> findAll() {
		return accountRepository.findAll();
	}
	
	public User createUser(AccountRequest userRequest) {
		User newUser = new User();
		return accountRepository.save(newUser);
	}

	public List<AccountInfo> getInfoAccount() {
		List<AccountInfo> accountInfos = accountRepository.getInfoAccount();
		for (AccountInfo accountInfo : accountInfos) {
			accountInfo.setRoles(roleRepository
					.findAllByStaffs(staffRepository
							.findByStaffIdAndDeleteFlagEquals(accountInfo.getStaff()
									.getStaffId(), Constants.NO)));
		}
		return accountInfos;
	}
}
