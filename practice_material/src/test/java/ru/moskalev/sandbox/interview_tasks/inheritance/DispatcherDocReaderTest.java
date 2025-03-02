package ru.moskalev.sandbox.interview_tasks.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DispatcherDocReaderTest {

    @Autowired
    DispatcherDocReader dispatcherDocReader;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void readXls() {
        dispatcherDocReader.read(DocType.XLS.name().toLowerCase());
        assertEquals("Xls read", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void readPdf() {
        dispatcherDocReader.read(DocType.PDF.name().toLowerCase());
        assertEquals("Pdf read", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void readWord() {
        dispatcherDocReader.read(DocType.WORD.name().toLowerCase());
        assertEquals("Word read", outputStreamCaptor.toString()
                .trim());
    }

}