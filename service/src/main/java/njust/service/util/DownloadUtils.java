package njust.service.util;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;


/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in 0:54 2018/2/19.
 * @since data-mining-platform
 */

public class DownloadUtils
{
    public static void bigFileDownload(HttpServletResponse response, String filePath,String suggestedFilename)
    {
        try
        {
            File file = new File(filePath);
            if (file.exists())
            {
                InputStream fis = new BufferedInputStream(new FileInputStream(file));
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(suggestedFilename, "utf-8"));
                response.addHeader("X-Suggested-Filename", suggestedFilename);
                response.addHeader("Content-Length", "" + file.length());
                OutputStream out = new BufferedOutputStream(response.getOutputStream());
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = fis.read(buffer)) != -1)
                {
                    out.write(buffer, 0, i);
                }
                fis.close();
                out.flush();
                out.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
