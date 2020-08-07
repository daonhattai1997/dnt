package dnt.repository;

import dnt.entity.User;
import dnt.entity.Data.AccountInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    
    //@Query("select u.username, u.staff from User u")
    @Query("select new dnt.entity.Data.AccountInfo(u.username, u.staff, u.createdDt) from User u")
    List<AccountInfo> getInfoAccount();

}
