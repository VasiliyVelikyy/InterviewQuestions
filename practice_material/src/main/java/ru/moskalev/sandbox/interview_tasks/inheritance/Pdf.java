package ru.moskalev.sandbox.interview_tasks.inheritance;

import org.springframework.stereotype.Service;

@Service
class Pdf implements DocReader {
    public void read() {
        System.out.println("Pdf read");
    }
}