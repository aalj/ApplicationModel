package net.lll0.base.utils.file;

import android.content.Context;
import android.os.Environment;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;


public class CacheFileUtil {
    private static final String PUSH_FILE_NAME = "push.log";
    private static final String LOG_FILE_NAME = "log.log";

    public static String getExternalCachePath(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/cache";
    }

    public static String getSystemDownloadPath(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/Download";
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 得到推动的日志的存储路径
     *
     * @param context
     * @return
     */
    public static String getExternalPushLog(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/log";
    }

    /**
     * 得到图片的缓存目录路径
     *
     * @param context
     * @return
     */
    public static String getExternalImag(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/image_thumbs";
    }

    /**
     * 获取Log日志的缓存目录的路径
     *
     * @param context
     * @return
     */
    public static String getExternalLog(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/log";
    }

    /**
     * 存推送日志
     *
     * @param context
     * @param msg
     */
    public static void writePustMessage(final Context context, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    File fileDir = new File(getExternalPushLog(context));

                    File file = new File(getExternalPushLog(context),
                            PUSH_FILE_NAME);
                    //第二个参数意义是说是否以append方式添加内容
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(msg + "\r\n");
                    bw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 存Log日志,
     *
     * @param context
     * @param msg
     */
    public static void writeLogMessage(final Context context, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    File fileDir = new File(getExternalLog(context));

                    File file = new File(getExternalLog(context),
                            LOG_FILE_NAME);
                    //第二个参数意义是说是否以append方式添加内容
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(msg + "\r\n");
                    bw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 获取问价的大小
     *
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }


    /**
     * 格式化文件大小的单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
