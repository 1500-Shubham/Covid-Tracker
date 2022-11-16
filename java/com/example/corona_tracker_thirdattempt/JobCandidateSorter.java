package com.example.corona_tracker_thirdattempt;

import java.util.ArrayList;
import java.util.Collections;

public class JobCandidateSorter {

    ArrayList<JobCandidates> jobCandidate = new ArrayList<>();
    public JobCandidateSorter(ArrayList<JobCandidates> jobCandidate) {
        this.jobCandidate = jobCandidate;
    }
    public ArrayList<JobCandidates> getSortedJobCandidateByAge() {
        Collections.sort(jobCandidate);
        return jobCandidate;
    }
}

