package cn.com.foton.Util;

/**
 * Created by Mr.peng on 2016/8/16.
 */
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * ��־�����:Ĭ���ǲ��Ի���<br>
 * <b>֧�֣��洢Log��־�ļ������ء�����Log��־��Ϣ��������</b>
 *
 * @author yuchao.wang
 * @since 2014-4-23
 */
public class LogUtil2 {

    /* ========================������Ǳ��ش洢��ص�========================== */
    /**
     * д��־����
     */
    private LogWriter logWriter;

    /**
     * д�뱾����־�߳�
     */
    private class LogWriter extends Thread {
        /**
         * �ļ�·��
         */
        private String mFilePath;
        /**
         * �����������߳�
         */
        private int mPid;
        /**
         * �߳����б�־
         */
        private boolean isRunning = true;

        /**
         * @param filePath �ļ�·��
         * @param pid
         */
        public LogWriter(String filePath, int pid) {
            this.mPid = pid;
            this.mFilePath = filePath;
        }

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);//���ڸ�ʽ������
            Process process = null;//����
            BufferedReader reader = null;
            FileWriter writer = null;
            try {
                //ִ��������
                String cmd = "logcat *:e *:w | grep";
                process = Runtime.getRuntime().exec(cmd);
                //�õ�������
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
                //�����ļ�
                File file = new File(mFilePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                writer = new FileWriter(file, true);
                //ѭ��д���ļ�
                String line = null;
                while (isRunning) {
                    line = reader.readLine();
                    if (line != null && line.length() > 0) {
                        writer.append("PID:" + this.mPid + "\t"
                                + sdf.format(new Date(System.currentTimeMillis())) + "\t" + line
                                + "\n");
                        writer.flush();
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                process = null;
                reader = null;
                writer = null;
            }
        }

        public void end() {
            isRunning = false;
        }
    }

    /**
     * ����Ӧ��ֻ��Ҫ����һ�μ���:��ʼ���ؼ�¼
     *
     * @param filePath Ҫд���Ŀ���ļ�·��
     * @param iswrite    �Ƿ���Ҫд��sdk
     */
    public void startWriteLogToSdcard(String filePath,boolean iswrite) {

        if (iswrite) {
            if (logWriter == null) {
                try {
                    /** LogUtil������pid,������������õ� */
                    logWriter = new LogWriter(filePath, android.os.Process.myPid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logWriter.start();
        }
    }

    /**
     * ����Ӧ��ֻ��Ҫ����һ�μ���:�������ؼ�¼
     */
    public void endWriteLogToSdcard() {
        if (logWriter != null) {
            logWriter.end();
        }
    }

    /* ========================���������Ҫ�ϴ�������========================== */
    private LogUploader logUploader;

    /**
     * ��־�ϴ��߳�
     */
    private class LogUploader extends Thread {
        /**
         * ��ǰ�߳��Ƿ���������
         */
        private boolean isRunning = true;
        /**
         * �ϴ�����Ҫ��url
         */
        private String mStrUrl;
        /**
         * �ϴ�����Ҫ����������
         */
        private HashMap<String, String> mAllParams;
        /**
         * �ϴ�����Ҫpid
         */
        private int mPid;

        /**
         * ���췽��
         *
         * @param strUrl    �ϴ�����Ҫ��url
         * @param allParams ��Ҫ�ϴ��Ķ���Ĳ�����������־���⡿
         * @param pid       ��־���ڵ�pid
         */
        public LogUploader(String strUrl, HashMap<String, String> allParams, int pid) {
            this.mStrUrl = strUrl;
            this.mAllParams = allParams;
            this.mPid = pid;
        }

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);//���ڸ�ʽ������
            Process process = null;//����
            BufferedReader reader = null;
            try {
                //ִ��������,�õ�������
                String cmd = "logcat *:e *:w | grep";
                process = Runtime.getRuntime().exec(cmd);
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
                String line = null;
                while (isRunning) {
                    line = reader.readLine();
                    if (line != null && line.length() > 0) {
                        String log = "PID:" + this.mPid + "\t"
                                + sdf.format(new Date(System.currentTimeMillis())) + "\t" + line;
                        mAllParams.put("log", log);
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                process = null;
                reader = null;
            }
        }

        public void end() {
            isRunning = false;
        }
    }

    /**
     * ����Ӧ�õ���һ�μ��ɣ��ϴ���־����
     *
     * @param strUrl    �ϴ�����Ҫ��url
     * @param allParams ��Ҫ�ϴ��Ķ���Ĳ�����������־���⡿
     * @param isUploadLog    �Ƿ���Ҫ�ϴ�
     */
    public void startUploadLog(String strUrl, HashMap<String, String>
            allParams,boolean isUploadLog) {

        if (isUploadLog) {
            if (logUploader == null) {
                logUploader = new LogUploader(strUrl, allParams, android.os.Process.myPid());
            }
            logUploader.start();
        }
    }

    /**
     * ����Ӧ�õ���һ�μ��ɣ������ϴ���־����
     */
    public void endUploadLog() {
        if (logUploader != null) {
            logUploader.end();
        }
    }

    /* ========================�������ֱ��ʹ�õ�========================== */

    /**
     * verbose��ϸ��־
     *
     * @param tag     ��־���
     * @param message ��־��Ϣ
     * @param isShowLog    �Ƿ���ʾ
     */
    public static void v(String tag, String message,boolean isShowLog) {

        if (isShowLog) {
            Log.v(tag, getDetailMessage(message));
        }
    }

    /**
     * verbose��ϸ��־
     *
     * @param message ��־��Ϣ
     * @param isShowLog    �Ƿ���ʾ
     */
    public static void v(String message,boolean isShowLog) {
        if (isShowLog) {
            String[] output = getTagAndDetailMessage(message);
            Log.v(output[0], output[1]);
        }
    }

    /**
     * error������־
     *
     * @param tag     ��־���
     * @param message ��־��Ϣ
     * @param isShowLog    �Ƿ���ʾ
     */
    public static void e(String tag, String message,boolean isShowLog) {

        if (isShowLog) {
            Log.e(tag, getDetailMessage(message));
        }
    }

    /**
     * error������־
     *
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void e(String message,boolean isShowLog) {

        if (isShowLog) {
            String[] output = getTagAndDetailMessage(message);
            Log.e(output[0], output[1]);
        }
    }

    /**
     * info��Ϣ��־
     *
     * @param tag     ��־���
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void i(String tag, String message,boolean isShowLog) {

        if (isShowLog) {
            Log.i(tag, getDetailMessage(message));
        }
    }

    /**
     * info��Ϣ��־
     *
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void i(String message,boolean isShowLog) {

        if (isShowLog) {
            String[] output = getTagAndDetailMessage(message);
            Log.i(output[0], output[1]);
        }
    }

    /**
     * debug������־
     *
     * @param tag     ��־���
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void d(String tag, String message,boolean isShowLog) {

        if (isShowLog) {
            Log.d(tag, getDetailMessage(message));
        }
    }

    /**
     * debug������־
     *
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void d(String message,boolean isShowLog) {

        if (isShowLog) {
            String[] output = getTagAndDetailMessage(message);
            Log.d(output[0], output[1]);
        }
    }

    /**
     * warn������־
     *
     * @param tag     ��־���
     * @param message ��־��Ϣ
     * @param isShowLog  isShowLog
     */
    public static void w(String tag, String message,boolean isShowLog) {

        if (isShowLog) {
            Log.w(tag, getDetailMessage(message));
        }
    }

    /**
     * warn������־
     *
     * @param message ��־��Ϣ
     * @param isShowLog    isShowLog
     */
    public static void w(String message,boolean isShowLog) {

        if (isShowLog) {
            String[] output = getTagAndDetailMessage(message);
            Log.w(output[0], output[1]);
        }
    }

    /**
     * �õ�Ĭ��tag���������Լ���Ϣ����
     *
     * @param message Ҫ��ʾ����Ϣ
     * @return Ĭ��tag���������Լ���Ϣ����,Ĭ����Ϣ���顾����+������+�к�+message��
     */
    private static String[] getTagAndDetailMessage(String message) {
        String output[] = new String[2];
        for (StackTraceElement ste : (new Throwable()).getStackTrace()) {
            //ջ���϶���LogUtil������Լ�
            if (LogUtil.class.getName().equals(ste.getClassName())) {
                continue;
            }
            //ջ������һ��������Ҫ���������ĵط�
            else {
                int b = ste.getClassName().lastIndexOf(".") + 1;
                output[0] = ste.getClassName().substring(b);
                output[1] = output[0] + "->" + ste.getMethodName() + "():" + ste.getLineNumber()
                        + "->" + message;
                break;
            }
        }
        return output;
    }

    /**
     * �õ�һ����Ϣ����ϸ�����������+������+�кš�
     *
     * @param message Ҫ��ʾ����Ϣ
     * @return һ����Ϣ����ϸ�����������+������+�к�+message��
     */
    private static String getDetailMessage(String message) {
        String detailMessage = "";
        for (StackTraceElement ste : (new Throwable()).getStackTrace()) {
            //ջ���϶���LogUtil������Լ�
            if (LogUtil.class.getName().equals(ste.getClassName())) {
                continue;
            }
            //ջ������һ��������Ҫ���������ĵط�[�˴�ȡ�������ͷ����������к�]
            else {
                int b = ste.getClassName().lastIndexOf(".") + 1;
                String TAG = ste.getClassName().substring(b);
                detailMessage = TAG + "->" + ste.getMethodName() + "():" + ste.getLineNumber()
                        + "->" + message;
                break;
            }
        }
        return detailMessage;
    }
}