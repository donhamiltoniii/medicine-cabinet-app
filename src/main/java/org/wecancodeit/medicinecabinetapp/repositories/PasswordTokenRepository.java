package org.wecancodeit.medicinecabinetapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.token.Token;
import org.wecancodeit.medicinecabinetapp.base.classes.User;
import org.wecancodeit.medicinecabinetapp.password.PasswordResetToken;

public interface PasswordTokenRepository extends CrudRepository<Token, User> {

	static void save(PasswordResetToken myToken) {
		
		
	}

}
