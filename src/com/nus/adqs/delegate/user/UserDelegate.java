package com.nus.adqs.delegate.user;

import com.nus.adqs.dataaccess.model.security.user.UserAccount;
import com.nus.adqs.dataaccess.model.security.user.UserInfo;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.user.UserService;

public class UserDelegate extends BaseDelegate<UserInfo> {
	public Class<UserService> getServiceClass(){return UserService.class;}
	private static UserDelegate instance;
	
	private UserDelegate(){	/*SINGLETON*/}
	public static UserDelegate getInstance(){
		if( UserDelegate.instance == null)
			UserDelegate.instance = new UserDelegate();
		
		return UserDelegate.instance;
	}
	
	/************EXTRAS**************/
	public UserAccount doGetUserAccountByUserName(String username)throws ValidationException, Exception{
		return super.getService(this.getServiceClass()).getUserAccountByUserName(username);
	}

	public UserAccount doGetUserAccountByEmail(String email)throws ValidationException, Exception{
		return super.getService(this.getServiceClass()).getUserAccountByEmail(email);
	}
	
	public UserAccount doAuthenticateUser(String username,String password)throws ValidationException, Exception{
		return super.getService(this.getServiceClass()).authenticateUser(username, password);
	}
	
}
