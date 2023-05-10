package com.github.tofpu.speedbridge2.database;

import org.hibernate.Session;

import java.util.function.Consumer;

public interface Database {
    void execute(Consumer<Session> sessionConsumer);
    void shutdown();

    default boolean supportsAsync() {
        return false;
    }
}
