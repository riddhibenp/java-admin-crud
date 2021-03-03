package com.example.cybage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cybage.entity.Profile;
import com.example.cybage.entity.User;
import com.example.cybage.entity.UserRepo;

@SpringBootApplication
public class JavaMajorG6Application implements CommandLineRunner {

	
	@Autowired
	UserRepo ur;
	public static void main(String[] args) {
		SpringApplication.run(JavaMajorG6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Profile p=new Profile();
		User u=new User("uname", "xyz@gmail.com", "123xyz", false, false, "kjfsgbikrg");
		p.setUser(u);
		u.setProfile(p);
		ur.save(u);

		
	}

}
