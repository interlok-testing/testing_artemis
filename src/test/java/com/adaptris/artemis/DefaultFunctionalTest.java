package com.adaptris.artemis;

import com.adaptris.testing.SingleAdapterFunctionalTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultFunctionalTest extends SingleAdapterFunctionalTest {

    @Test
    public void test() throws Exception {
        WatchKey watchKey = waitForFileEvent(Path.of(new File("messages").getAbsolutePath()), 15000, StandardWatchEventKinds.ENTRY_CREATE);
        AtomicInteger messageCount = new AtomicInteger();
        watchKey.pollEvents().forEach(event -> {
            messageCount.getAndIncrement();
        });
        assert messageCount.get() > 0;
    }
}
