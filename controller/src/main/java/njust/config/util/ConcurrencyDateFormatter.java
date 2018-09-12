package njust.config.util;


import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in 16:40 2018/3/5.
 * @since data-mining-platform
 */

public class ConcurrencyDateFormatter extends SimpleDateFormat
{
    private static final long serialVersionUID = -2444650236995557863L;

    // 委托给线程安全的类反序列化
    @Override
    public Date parse(String source)
        throws ParseException
    {
        return ConcurrentDateUtil.parse(source);
    }


    @Override
    public Date parse(String text, ParsePosition pos) {
        return ConcurrentDateUtil.parse(text, pos);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        return ConcurrentDateUtil.format(date, toAppendTo, pos);
    }


}
