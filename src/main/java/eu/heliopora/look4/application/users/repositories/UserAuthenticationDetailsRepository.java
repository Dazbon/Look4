package eu.heliopora.look4.application.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.heliopora.look4.application.users.entities.UserAuthenticationDetail;

@Repository
public interface UserAuthenticationDetailsRepository extends JpaRepository<UserAuthenticationDetail, Long> {

	UserAuthenticationDetail findByUsername(String username);
	
}