package njust.util;

/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in 13:31 2017/7/30.
 * @since data-mining-platform
 */

public class ExceptionUtil
{
    public final static String AUTHENTICATION_EXCEPTION_MESSAGE = "User Account ";
    public static String getRootMessage(Throwable throwable)
    {
        if (throwable == null)
        {
            return null;
        }
        String error = throwable.getMessage();
        Throwable nested = throwable;
        while (nested.getCause() != null)
        {
            nested = nested.getCause();
            error = nested.getMessage();
        }
        return error;
    }

}
