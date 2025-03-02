package ru.moskalev.sandbox.interview_tasks.inheritance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
class DispatcherDocReader {
    public final Map<String, DocReader> dockReaders;

    public void read(String dockType) {
        dockReaders.get(dockType).read();
    }
}
