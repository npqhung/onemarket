package com.onesys.onemarket.utils;

/**
 * Created by Hung on 25/03/2015.
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }

    public static String getPriceFormat(String price)
    {
        Double localDouble = Double.valueOf(Double.parseDouble(price));
        return new DecimalFormat("#,###,### " + "VND").format(localDouble);
    }

}
