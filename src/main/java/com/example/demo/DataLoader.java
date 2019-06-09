package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  InterviewRepository interviewRepository;


  //delete this before merge
  @Autowired
 JobRepo jobRepo;

  @Override
  public void run(String... strings) throws Exception{
    roleRepository.save(new Role("USER"));
    roleRepository.save(new Role("ADMIN"));

    Role adminRole = roleRepository.findByRole("ADMIN");
    Role userRole = roleRepository.findByRole("USER");


      ArrayList<String> resume = new ArrayList<String>();
      resume.add("Java"); //**
      resume.add("job");
      resume.add("anthony");
      resume.add("valle");
      resume.add("excel");//**
      resume.add("git");//**
      resume.add("client");
      resume.add("millions");
      resume.add("analytics");
      resume.add("python");//**
      resume.add("clients");
      resume.add("i");
      resume.add("need");
      resume.add("a");
      resume.add("job");
      resume.add("office");
      resume.add("postgress");//** take this one out

    User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true,
            "jim");
    user.setRoles(Arrays.asList(userRole));
    user.setResult(resume);
    userRepository.save(user);

    user = new User("admin@admin.com", "password",
            "Admin",
            "User", true,
            "admin");
    user.setRoles(Arrays.asList(adminRole));
    userRepository.save(user);

      Job myjob = new Job();
      myjob.setCompanyName("Twitter");
      myjob.setPositionTitle("Java Dev");
      myjob.setKeyWord("Java,Python,SQL,Excel,postgress,git");
      myjob.setTypeOfJob("Full Time");
      myjob.setSalary(100000.00);
      myjob.setDescription("This is a demo job");
      myjob.setQuestionOne("What is the JVM?");
      myjob.setQuestionTwo("What is OOP?");
      myjob.setQuestionThree("what is java?");
      jobRepo.save(myjob);

//      Interview interview = new Interview("This is Question 1", "This is Question 2","This is Question 3",
//              "This is Question 4","This is Question 5", "This is Question 6");
//    interviewRepository.save(interview);
//



  }
}
