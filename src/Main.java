import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<File> files = new ArrayList<>();

    public static void findAllFilesInFolder(File folder) {
        for (File file: folder.listFiles()) {
            if (!file.isDirectory()) {
                files.add(file);
            } else {
                findAllFilesInFolder(file);
            }
        }
    }

    public static String readYear(File file) {
        String year = "";
        if (file.getName().startsWith("IMG-") || file.getName().startsWith("VID-")) {
            year = file.getName().substring(4, 8);
        } else if (file.getName().startsWith("FB_IMG") || file.getName().startsWith("Screen")) {
            return "others";
        } else {
            year = file.getName().substring(0, 4);
        }
        return year;

    }

    public static String readMonth(File file) {
        String month = "";
        if (file.getName().startsWith("IMG-") || file.getName().startsWith("VID-")) {
            month = file.getName().substring(8, 10);
        } else if (file.getName().startsWith("FB_IMG") || file.getName().startsWith("Screen")) {
            return "others";
        } else {
            month = file.getName().substring(4, 6);
        }
        return month;

    }

    private static void moveFiles(List<File> files) throws IOException {
        for (File file : files) {
            String target = "C:\\+originPath+\\" + readYear(file) + "\\" + readMonth(file) + "\\" + file.getName();
            File olfFile = new File(file.getAbsolutePath());
            File newFile = new File(target);
            newFile.mkdirs();


            Files.move(olfFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void main(String[] args) throws IOException {
        String originPath = "/teste";
        File folder = new File(originPath);

        findAllFilesInFolder(folder);
        moveFiles(files);
    }


}
