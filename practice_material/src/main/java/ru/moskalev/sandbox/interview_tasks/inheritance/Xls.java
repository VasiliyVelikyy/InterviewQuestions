package ru.moskalev.sandbox.interview_tasks.inheritance;

import org.springframework.stereotype.Service;

@Service
class Xls implements DocReader {
    public void read() {
        System.out.println("Xls read");
    }
}