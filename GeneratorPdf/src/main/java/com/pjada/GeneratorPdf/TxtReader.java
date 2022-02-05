package com.pjada.GeneratorPdf;

import java.io.*;

public class TxtReader {
    private File file;

    public String readFile(){
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(reader);
        }
        return builder.toString();
    }

    private void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {}
        }
    }

    public TxtReader(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
