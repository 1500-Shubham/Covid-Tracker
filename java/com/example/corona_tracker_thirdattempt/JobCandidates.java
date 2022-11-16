package com.example.corona_tracker_thirdattempt;

public class JobCandidates implements Comparable<JobCandidates> {
    private String name;
    private String gender,newcases,recovered;
    private int age;
    public JobCandidates(String name, String gender, int age,String newcases,String recovered) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.newcases=newcases;
        this.recovered=recovered;
    }
    public String getName() {
        return name;
    }
    public String getRecovered() {
        return recovered;
    }
    public String getNewcases() {
        return newcases;
    }
    public String getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(JobCandidates candidate) {
        return (this.getAge() > candidate.getAge() ? -1 :
                (this.getAge() == candidate.getAge() ? 0 : 1));
    }
    @Override
    public String toString() {
        return " Name: " + this.name + ", Gender: " + this.gender + ", age:" + this.age +", newcases:"+this.newcases +", recovered:"+this.recovered;
    }
}