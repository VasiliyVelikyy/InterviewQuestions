package ru.moskalev.sandbox.interview_tasks.inheritance;

import org.springframework.stereotype.Service;

@Service
class Word implements DocReader {
    public void read() {
        System.out.println("Word read");
    }
}

