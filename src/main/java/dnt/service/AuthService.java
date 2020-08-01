package dnt.service;

import dnt.entity.EnumType.RoleName;
import dnt.entity.Hotel;
import dnt.entity.Role;
import dnt.entity.Staff;
import dnt.entity.User;
import dnt.entity.UserPrincipal;
import dnt.entity.Data.Constants;
import dnt.entity.Data.RegisterRequest;
import dnt.exception.ApplicationException;
import dnt.repository.AccountRepository;
import dnt.repository.GroupRepository;
import dnt.repository.HotelRepository;
import dnt.repository.RoleRepository;
import dnt.repository.StaffRepository;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Setter(onMethod = @__(@Autowired))
public class AuthService implements UserDetailsService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private GroupRepository groupRepository;
    private StaffRepository staffRepository;
    private RoleRepository roleRepository;
    private HotelRepository hotelRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User account = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tim thay tai khoan " + userName));
        account.getStaff().setRoles(findAllByStaffs(account.getStaff().getStaffId()));

        return UserPrincipal.create(account);
    }
    
    public Staff createStaff(RegisterRequest registerRequest) throws Exception {
    	Staff newStaff = new Staff();
    	newStaff.setName(registerRequest.getName());
    	newStaff.setAddress(registerRequest.getAddress());
    	newStaff.setEmail(registerRequest.getEmail());
    	newStaff.setGender(registerRequest.getGender());
    	Hotel hotel = hotelRepository.findByHotelName(registerRequest.getHotelName())
				 .orElseThrow(() -> new Exception("khong tim thay " + registerRequest.getHotelName()));
    	newStaff.setHotel(hotel);
    	newStaff.setCreatedDt(new Date());
    	newStaff.setUpdatedDt(new Date());
    	newStaff.setDeleteFlag(Constants.NO);
    	return staffRepository.save(newStaff);
    }
    
//    public Staff register(RegisterRequest registerRequest) {
//        if (accountRepository.existsByUsername(registerRequest.getUsername())) {
//            throw new ApplicationException("Username da duoc su dung");
//        }
//
//        User account = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
//        final Staff staff = new Staff();
//
//        if (registerRequest.getOwnGroupIds() != null) {
//            registerRequest.getOwnGroupIds()
//                    .forEach(grId -> staff.addOwn(groupRepository.findById(grId)
//                            .orElseThrow(() -> new ApplicationException("Khong ton tai nhom"))));
//        }
//        if (registerRequest.getOwnedByGroupIds() != null) {
//            registerRequest.getOwnedByGroupIds()
//                    .forEach(grId -> staff.addOwnedBy(groupRepository.findById(grId)
//                            .orElseThrow(() -> new ApplicationException("Khong ton tai nhom"))));
//        }
//
//        staff.setAddress(registerRequest.getAddress());
//        staff.setEmail(registerRequest.getEmail());
//        staff.setGender(registerRequest.getGender());
//        staff.setName(registerRequest.getName());
//        mapRolesFromRequest(registerRequest).forEach(staff::addRole);
//
//        account.setToStaff(staff);
//        return staffRepository.save(staff);
//    }

//    private Set<Role> mapRolesFromRequest(RegisterRequest registerRequest) {
//        return registerRequest.getRoles().stream()
//                .map(role -> {
//                    log.info(RoleName.valueOf(role).toString());
//                    return roleRepository.findByName(RoleName.valueOf(role))
//                            .orElseThrow(() -> new ApplicationException("Khong ton tai role"));
//                })
//                .collect(Collectors.toSet());
//    }

    public List<Role> findAllByStaffs(int staff_id) {
        return roleRepository.findAllByStaffs(staffRepository.findAllByStaffId(staff_id));
    }
}
