package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String date;

    private String intTime;

    private String behQuest1;
    private String behQuest2;
    private String behQuest3;

    private String jobQuest1;
    private String jobQuest2;
    private String jobQuest3;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer6;

    private String transcript;

//This below has to be rethunk
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy = "interview")
    private Job job;


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }


    public Interview() {
    }

    public Interview(String behQuest1, String behQuest2, String behQuest3, String jobQuest1, String jobQuest2, String jobQuest3) {
        this.behQuest1 = behQuest1;
        this.behQuest2 = behQuest2;
        this.behQuest3 = behQuest3;
        this.jobQuest1 = jobQuest1;
        this.jobQuest2 = jobQuest2;
        this.jobQuest3 = jobQuest3;
    }

    public void setJobQuest1(String jobQuest1) {
        this.jobQuest1 = jobQuest1;
    }

    public void setJobQuest2(String jobQuest2) {
        this.jobQuest2 = jobQuest2;
    }

    public void setJobQuest3(String jobQuest3) {
        this.jobQuest3 = jobQuest3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntTime() {
        return intTime;
    }

    public void setIntTime(String intTime) {
        this.intTime = intTime;
    }

    public String getBehQuest1() {
        return behQuest1;
    }

    public void setBehQuest1(String behQuest1) {
        this.behQuest1 = behQuest1;
    }

    public String getBehQuest2() {
        return behQuest2;
    }

    public void setBehQuest2(String behQuest2) {
        this.behQuest2 = behQuest2;
    }

    public String getBehQuest3() {
        return behQuest3;
    }

    public void setBehQuest3(String behQuest3) {
        this.behQuest3 = behQuest3;
    }

    public String getJobQuest1() {
        return jobQuest1;
    }

    public String getJobQuest2() {
        return jobQuest2;
    }

    public String getJobQuest3() {
        return jobQuest3;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    public String getAnswer6() {
        return answer6;
    }

    public void setAnswer6(String answer6) {
        this.answer6 = answer6;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
}
