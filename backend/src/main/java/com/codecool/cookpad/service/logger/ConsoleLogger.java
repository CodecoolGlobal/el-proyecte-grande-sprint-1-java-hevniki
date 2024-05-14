package com.codecool.cookpad.service.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {
    static Date date = new Date();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    @Override
    public void logMessage(String message) {
        var currentTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        var date = new Date(currentTime);
        stringBuilder.append(dateFormat.format(date));
        stringBuilder.append(" ---INFO--- ");
        stringBuilder.append(message);

        System.out.println(stringBuilder);
    }

    @Override
    public void logError(String error) {
        var currentTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        var date = new Date(currentTime);
        stringBuilder.append(dateFormat.format(date));
        stringBuilder.append(" ---ERROR--- ");
        stringBuilder.append(error);

        System.out.println(stringBuilder);
    }
}
