package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobMethods {

@Autowired
User user;

@Autowired
Job job;



    public List<String> SplitKeyWords(String key1) { //updated to split up words

        List<String> items = Arrays.asList(key1.split("\\s*,\\s*"));
        return items;
    }
/*
 In the home controller for the page that allows users to apply for a job, the method should be called;
 the user applying for the job should be passed in the method as well as the job that the user is applying to. The method
 will return a boolean value (if true is returned user meets qualifications if false is returned user does not meet
 qualifications). The boolean should trigger a label on the webpage to display whether a user has been selected for an
 interview.
 */


    public boolean compareTool(User user, Job job){
        ArrayList<String> userResume = user.getResult();
        List<String> jobKeywords =SplitKeyWords(job.getKeyWord());
        int counter=0;
        int keyWordCount = jobKeywords.size();
        for (String word:jobKeywords){
            for (String key:userResume){
                if (word.equalsIgnoreCase(key)){
                    counter++;
                }
            }
        }
        double mainScore = (keyWordCount*.8);

        if ( counter > mainScore){
            return true;
        }
        else {
            return false;
        }

    }

}

