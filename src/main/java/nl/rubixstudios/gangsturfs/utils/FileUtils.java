package nl.rubixstudios.gangsturfs.utils;

import nl.rubixstudios.gangsturfs.GangsTurfs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static File getOrCreateFile(File parent, String name) {
        File file = new File(parent, name);

        if(!parent.exists()) parent.mkdir();

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public static void removeFile(String name) {
        final File file = new File(GangsTurfs.getInstance().getDataFolder() + name);

        file.delete();
    }

    public static File[] getAllFiles(final File parent) {
        List<File> files = new ArrayList<>();

        for (String s : parent.list()) {
            File file = new File(parent, s);

            if(!parent.exists()) parent.mkdir();

            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            files.add(file);
        }
        return files.toArray(new File[files.size()]);
    }

    public static void writeString(File file, String content) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(content);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String readWholeFile(File file) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            StringBuilder builder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.length() != 0 ? builder.toString() : null;

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Instant creationDate(File file) {
        try {

            BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return attributes.creationTime().toInstant();

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
