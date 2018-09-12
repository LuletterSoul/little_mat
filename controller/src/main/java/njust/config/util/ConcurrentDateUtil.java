package njust.config.util;


import java.text.*;
import java.util.Date;


/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in 16:40 2018/3/5.
 * @since data-mining-platform
 */

public class ConcurrentDateUtil
{

    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(
        () -> new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));

    public static Date parse(String dateStr)
        throws ParseException
    {
        return threadLocal.get().parse(dateStr);
    }

    public static Date parse(String source, ParsePosition pos)
    {
        return threadLocal.get().parse(source, pos);
    }

    public static String format(Date date)
    {
        return threadLocal.get().format(date);
    }

    public static StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        return threadLocal.get().format(date, toAppendTo, pos);
    }
}
