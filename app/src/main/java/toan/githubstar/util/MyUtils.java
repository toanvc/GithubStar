package toan.githubstar.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class MyUtils {
    @SuppressLint("SimpleDateFormat")
    public static String getPastSevenDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * @param input : 2016-05-31T08:24:34Z
     * @return 2016-05-31
     */
    public static String formatInputDate(String input) {
        return input.substring(0, input.indexOf('T'));
    }
}
