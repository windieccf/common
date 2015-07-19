package com.nus.adqs.service.user;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.security.user.UserAccount;
import com.nus.adqs.dataaccess.model.security.user.UserInfo;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.BaseService;

@ServiceRegistry(className="com.nus.adqs.service.user.impl.UserServiceImpl")
public interface UserService extends BaseService<UserInfo>{
	
	public UserAccount getUserAccountByUserName(String username) throws ValidationException, Exception;

	public UserAccount getUserAccountByEmail(String email) throws ValidationException, Exception;
	
	public UserAccount authenticateUser(String username, String password) throws ValidationException, Exception;
	
}
