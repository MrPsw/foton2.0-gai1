package cn.com.foton.Util;

/**
 * Created by Mr.peng on 2016/8/15.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڲ���������.
 *
 * @author shimiso
 */

public class DateUtil {

    /**
     * Ӣ�ļ�д�磺2010
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * Ӣ�ļ�д�磺12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * Ӣ�ļ�д�磺1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * Ӣ�ļ�д��Ĭ�ϣ��磺2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * Ӣ��ȫ��  �磺2010-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * Ӣ��ȫ��  �磺2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * ��ȷ�����������ʱ��    �磺yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * ��ȷ�����������ʱ��    �磺yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * ���ļ�д  �磺2010��12��01��
     */
    public static String FORMAT_YMD_CN = "yyyy��MM��dd��";

    /**
     * ���ļ�д  �磺2010��12��01��  12ʱ
     */
    public static String FORMAT_YMDH_CN = "yyyy��MM��dd�� HHʱ";

    /**
     * ���ļ�д  �磺2010��12��01��  12ʱ12��
     */
    public static String FORMAT_YMDHM_CN = "yyyy��MM��dd�� HHʱmm��";

    /**
     * ����ȫ��  �磺2010��12��01��  23ʱ15��06��
     */
    public static String FORMAT_YMDHMS_CN = "yyyy��MM��dd��  HHʱmm��ss��";

    /**
     * ��ȷ���������������ʱ��
     */
    public static String FORMAT_FULL_CN = "yyyy��MM��dd��  HHʱmm��ss��SSS����";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }


    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }


    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }


    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }



    /**
     * ��õ�ǰ���ڵ��ַ�����ʽ
     * @param format    ��ʽ��������
     * @return  ���ظ�ʽ��֮����¼�
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);

    }


    /**
     *
     * @param time ��ǰ��ʱ��
     * @return  ��ʽ����
     */
    //
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }


    /**
     *
     * @param time  ��ǰ��ʱ��
     * @return  ��ǰ����
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }
    //��õ��µ�һ��
    public static String GetMONTH_1(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //��ȡ��ǰ�µ�һ�죺
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        //����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
        c.set(Calendar.DAY_OF_MONTH,1);
        String first = format.format(c.getTime());
        System.out.println("===============first:"+first);
        return first;
    }

    /**
     *
     * @param time ʱ��
     * @return ����һ������
     */
    // ��ʽ������
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }


    /**
     * ��������������������
     * @param date ����
     * @param n Ҫ���ӵ�����
     * @return   ������������
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }


    /**
     * ����������������
     * @param date ����
     * @param n Ҫ���ӵ�����
     * @return   ����֮�������
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }


    /**
     * ��ȡ������ĳһСʱ��ʱ��
     *
     * @param format ��ʽ��ʱ��ĸ�ʽ
     * @param h �����ڵ�Сʱ ���磺h=-1Ϊ��һ��Сʱ��h=1Ϊ��һ��Сʱ
     * @return  ��ȡ������ĳһСʱ��ʱ��
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }


    /**
     * ��ȡʱ���
     * @return ��ȡʱ���
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }



    /**
     * ����������������
     *
     * @param date Date ����
     * @return �����·�
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * ����������������
     *
     * @param date Date ����
     * @return �����շ�
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * ��������������С
     *
     * @param date ����
     * @return ����Сʱ
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * �������������ط�
     *
     * @param date ����
     * @return ���ط���
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * ���Ĭ�ϵ� date pattern
     * @return  Ĭ�ϵĸ�ʽ
     */
    public static String getDatePattern() {

        return FORMAT_YMDHMS;
    }


    /**
     * ��������
     *
     * @param date Date ����
     * @return ��������
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     * ʹ��Ԥ���ʽ��ȡ�ַ�������
     *
     * @param strDate �����ַ���
     * @return ��ȡ�ַ���������
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());

    }


    /**
     * �������������غ�
     *
     * @param date ����
     * @return ���غ�
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * ��Ĭ�ϸ�ʽ���ַ���������������
     *
     * @param date �����ַ���
     * @return ��Ĭ�ϸ�ʽ���ַ���������������
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }


    /**
     * ʹ���û���ʽ��ȡ�ַ�������
     *
     * @param strDate �����ַ���
     * @param pattern ���ڸ�ʽ
     * @return  ��ȡ�ַ�������
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * ���û���ʽ�ַ���������������
     *
     * @param date �����ַ���
     * @param format ���ڸ�ʽ
     * @return  ���û���ʽ�ַ���������������
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }
}
