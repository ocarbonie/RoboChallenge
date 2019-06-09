package com.example.demo;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class HomeController {
    User user;

    @Autowired
    JobRepo jobRepo;

    @Autowired
    private JavaMailSender sender;
    Path fi; // to check for sent email with
    String fname;

    @Autowired
    UserService userService;
    ArrayList<String> arrayList = new ArrayList<>();
    String email;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {

        if (result.hasErrors()) {
            return "registration";
        } else {
            if (file.isEmpty()) {
                return "redirect:/register";
            }
            try {

                byte[] bytes = file.getBytes();
                Path path = Paths.get(file.getOriginalFilename());
                fi = path;
                Files.write(path, bytes);
                String filename = file.getOriginalFilename();

                user.setFilename(filename);
                fname = filename;
                try (Scanner s = new Scanner(new File(filename)).useDelimiter(" ")) {
                    // \\s* in regular expressions means "any number or whitespaces".
                    // We could've said simply useDelimiter("-") and Scanner would have
                    // included the whitespaces as part of the data it extracted.
                    while (s.hasNext()) {
                        arrayList.add(s.next());
                    }
                } catch (FileNotFoundException e) {
                    // Handle the potential exception
                }


            } catch (IOException e) {

                e.printStackTrace();

                return "redirect:/register";

            }
            user.setResult(arrayList);
            userService.saveUser(user);
            email = user.getEmail();
            model.addAttribute("message", "User Account Created");
            if(email==user.getEmail()){
                try {
                    sendEmail();
                } catch (Exception ex) {
                    return "Error in sending email: " + ex;
                }

            }


        }

        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")

    public String listJobs(Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        homes();
        return "list";




    }

    public String getCurrentTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);

    }

    @RequestMapping("/send")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }

    private void sendEmail() throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        //helper.setTo("fikru07@gmail.com");
        helper.setText("Well Come to job finder.com ");
        helper.setSubject("registration confirm");

        sender.send(message);
    }


    @RequestMapping("/send2")
    @ResponseBody
    String homes() {
        try {
            sendEmails();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }

    private void sendEmails() throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setText("");
        helper.setSubject("interview and answers document");

        //ClassPathResource file = new ClassPathResource(fname);
        //helper.addAttachment(fname,file);
        FileSystemResource file = new FileSystemResource(fname);
        helper.addAttachment(file.getFilename(), file);

        sender.send(message);
    }


}


