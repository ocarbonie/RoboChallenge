package com.example.demo;


import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class HomeControllerOona {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    JobRepo jobRepo;

    @RequestMapping("/interviews")
    public String listInterviews( Model model) {
//        model.addAttribute("job", jobRepo.findAll());
        model.addAttribute("interviews", interviewRepository.findAll());

        return "interviewList";
    }

//    @GetMapping("/intRegister/{id}")
//    public String InterviewReg(@PathVariable("id") long id, Interview interview, Model model) {
//        model.addAttribute("job", jobRepo.findById(id).get());
//        interviewRepository.save(interview);
//        return "interviewRegistration";
//    }
//

//    @RequestMapping("/intRegister/{id}")
//    public String InterviewReg(@PathVariable("id") long id, Model model) {
//        model.addAttribute("job", jobRepo.findById(id).get());
////        model.addAttribute("interview", new Interview());
//
//        return "redirect:/intRegister";
//    }

    @GetMapping("/intRegister")
    public String interviewRegistrationForm(Model model){
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("interview", new Interview());
        return "interviewRegistration";

    }
    @PostMapping("/processIntRegister")
    public String processInterviewReg(@ModelAttribute Interview interview, Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("interviews" ,interviewRepository.findAll());
        interviewRepository.save(interview);

        return "redirect:/interviews";
    }

    @GetMapping("/intQuest")
    public String Interview(Model model) {
        model.addAttribute("interview", new Interview());
        return "interviewTemp";
    }

    @PostMapping("/intQuest")
    public String homePage(Interview interview, Model model) throws IOException {
        String q1= interview.getBehQuest1();
        String a1= interview.getAnswer1();

        String q2= interview.getBehQuest2();
        String a2= interview.getAnswer2();

        String q3= interview.getBehQuest3();
        String a3= interview.getAnswer3();

        String q4= interview.getJobQuest1();
        String a4= interview.getAnswer4();

        String q5= interview.getJobQuest2();
        String a5= interview.getAnswer5();

        String q6= interview.getJobQuest3();
        String a6= interview.getAnswer6();

        String content = q1 +a1+ q2 +a2 + q3+ a3+ q4+ a4+ q5 +a5 +q6 +a6;

        FileWriter fileWriter = new FileWriter("file.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
//        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();
        File f = new File("file.txt");
        Map options = ObjectUtils.asMap(
                "public_id", "file",
                "resource_type", "raw"
        );
        Map uploadResult =
                cloudc.upload(f, options);
        return "redirect:/interviews";

    }


}


