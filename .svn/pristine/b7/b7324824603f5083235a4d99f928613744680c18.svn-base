package com.nus.adqs.util.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.dataaccess.persistence.SystemSetupHome;
import com.nus.adqs.util.StringUtil;

public class MailUtil {

	/********************************/
	private String from;
	private List<String> receipients = new ArrayList<String>();
	private String subject;
	private String content;
	private String host;
	private boolean requiredAUthentication = true;
	private String username;
	private String password;
	@SuppressWarnings("unused")
	private String auth;
	private String port;
	private boolean enableTtls = false;
	
	
	private MailUtil(){}
	private MailUtil(String host){this.host = host;}
	/************* START DSL ***********/
	
	public MailUtil host(String host){
		this.host = host;
		return this;
	}
	
	public MailUtil from(String from){
		this.from = from;
		return this;
	}

	public MailUtil port(String port){
		this.port = port;
		return this;
	}

	public MailUtil auth(String auth){
		this.auth = auth;
		return this;
	}
	
	public MailUtil enableTtls(){
		this.enableTtls = true;
		return this;
	}
	
	public MailUtil addReceipients(String... receipients){
		if(receipients!=null){
			this.receipients.addAll(Arrays.asList(receipients));
		}
		return this;
	}
	
	
	public MailUtil subject(String subject){
		this.subject = subject;
		return this;
	}
	
	public MailUtil content(String content){
		this.content = content;
		return this;
	}
	
	public MailUtil credential(String username, String password){
		this.username = username;
		this.password = password;
		return this;
	}
	
	
	public void send() throws Exception{
		this.validate();
		
		Properties properties = System.getProperties();
		Session session = null;
		properties.setProperty("mail.smtp.host", StringUtil.isEmpty(host) ? SystemSetupHome.getParam().getEmailHost(): host);
		if(requiredAUthentication){
			properties.put("mail.smtp.host", this.host);
			properties.put("mail.smtp.user", this.username);
			properties.put("mail.smtp.password", this.password);
			properties.put("mail.smtp.port", this.port);
			if(this.enableTtls)
				properties.put("mail.smtp.starttls.enable", "true");
			
			if(this.requiredAUthentication)
				properties.put("mail.smtp.auth", "true");
			
			session = Session.getDefaultInstance(properties, 
					new javax.mail.Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(username,password); }
					}
		        );
		}else
			session = Session.getDefaultInstance(properties);
		
		MimeMessage message = new MimeMessage(session);
        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To
        Address[] addresses = InternetAddress.parse(StringUtils.join(this.receipients , ","));
        message.addRecipients(Message.RecipientType.TO, addresses);
        
        // Set Subject: header field
        message.setSubject(this.subject);

        // Send the actual HTML message, as big as you like
        message.setContent(this.content , "text/html");
        Transport.send(message);
		
	}
	
	private void validate() throws Exception{
		
		if(StringUtil.isEmpty(this.from))
			throw new IllegalArgumentException("MailUtil.send : from is required");
		
		if(this.receipients.isEmpty())
			throw new IllegalArgumentException("MailUtil.send : receipients is required");
		
		
		
	}
	
	
	
	/************************* INIT CALLER ***************************/
	
	public static MailUtil create(String host){
		return new MailUtil(host);
	}
	
	public static MailUtil create(){
		return new MailUtil();
	}

	
	public static void main(String... args){
		
		StringBuffer content = new StringBuffer()
					.append("Hello All, <br/>")
					.append("This Email is a proof that it is working")
					.append("");
		
		try{
			MailUtil.create("smtp.gmail.com")
			.from("simadqs")
			.port("587")
			.enableTtls()
			.addReceipients("mansoor.mi08@gmail.com","kenny1har@gmail.com","robin.foe@gmail.com")
			.subject("Testing Email Notifier")
			.content(content.toString())
			.credential("simadqs", "simadqspassword")
			.send();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
