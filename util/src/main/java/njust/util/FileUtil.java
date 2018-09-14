package njust.util;

import java.io.File;

public class FileUtil {
    public static boolean deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists())return file.delete();
        else return false;
    }
}
