package org.wecancodeit.medicinecabinetapp.service;

import org.wecancodeit.medicinecabinetapp.base.classes.User;

public interface UserService {
	 void save(User user);

	    User findByUsername(String username);

		User findUserByEmail(String userEmail);

		void createPasswordResetTokenForUser(User user, String token);
}
