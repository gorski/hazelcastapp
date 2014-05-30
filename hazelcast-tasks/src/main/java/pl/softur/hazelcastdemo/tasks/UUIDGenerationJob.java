package pl.softur.hazelcastdemo.tasks;

import java.io.*;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Fake job task, returns UUID.
 */
public class UUIDGenerationJob implements Callable<String>, Serializable {

    @Override
    public String call() {
        final long start = new Date().getTime();
        final String uuid = UUID.randomUUID().toString();
        System.out.println("UUIDGenerationJob: start");
        PrintWriter writer = null;
        final String TXT = "uuid" + File.separator + uuid + ".txt";
        final String ZIP = "uuid" + File.separator + uuid + ".zip";
        try {
            writer = new PrintWriter(TXT, "UTF-8");
            writer.println(uuid);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("UUIDGenerationJob: File saved :" + TXT);

        compress(TXT, ZIP);

        System.out.println("UUIDGenerationJob: File compressed :" + TXT);

        final long delta = new Date().getTime() - start;
        System.out.println("UUIDGenerationJob: done in [" + delta + "] ms");
        return uuid + ";" + delta;
    }


    private void compress(String source, String destination) {
        byte[] buffer = new byte[1024];
        try {

            FileOutputStream fos = new FileOutputStream(destination);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(source);
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(source);

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();
            zos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
