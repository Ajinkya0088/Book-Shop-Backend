package com.library.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Users;
import com.library.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	public Users adduser(@RequestBody Users user)
	{
		return userRepository.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userRepository.deleteById(id);
	}
	
	@GetMapping("/users")
	public List <Users> getUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/login")
	public Users login(@RequestHeader("email") String email, @RequestHeader("password") String password) {
		Users u = userRepository.findByEmail(email);
		if (u != null) {
			if (password.equals(u.getPassword())) {
				return u;
			}
		}
		throw new RuntimeException("Credentials Invalid");

	}
	@PostMapping("/verifymail")
	public void generateCode(@RequestHeader("email") String email) {
		int code = (int) (Math.random() * 100000);

		if (userRepository.findByEmail(email)!= null) {
			map.put(email, code);
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("Enter your email", "password");
				}
			});
			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(email, false));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				msg.setSubject("Verify Using code...");
				msg.setContent("Dear User, " + " Your Reset password Code is :" + code, "text/html");
				msg.setSentDate(new Date());
				Transport.send(msg);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		else {
			throw new RuntimeException("Email not found");
		}
	}
	HashMap<String, Integer> map=new HashMap<>();
	@GetMapping("/reset/{code}")
	public Users verifyCode(@RequestHeader("email") String email, @RequestHeader("password") String password,
			@PathVariable("code") int code) {
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			if (e.getKey().equals(email)) {
				if (e.getValue() == code) {
					map.remove(email);
					Users u = userRepository.findByEmail(email);
					u.setPassword(password);
					userRepository.save(u);
					return u;
				}
			}
		}
		throw new RuntimeException("Credentials Invalid");
	}
}
