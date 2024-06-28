package com.employee.portal.factory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Logger {
    public FileWriter fw;
    public BufferedWriter bw;

    public static Logger lg;

    static {
        try {
            lg = new Logger();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Logger() throws IOException {
        fw = new FileWriter("empPortalLogs.txt", true);
        bw = new BufferedWriter(fw);
    }

    public static Logger getInstance() {
        return lg;
    }

    public void log(String msg) throws IOException {
        String timeStamp = new SimpleDateFormat("[yyyy/MM/dd ; HH:mm:ss]").format(new java.util.Date());
        this.bw.write(timeStamp + " " +msg);
        this.bw.newLine();
        this.bw.flush();
    }
}
