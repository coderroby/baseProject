package com.myres.noban.mykotlinmvpproject.utility;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Rafiqul Hasan Rony on 1/29/19.
 * Audacity It Solution.
 */

public class Utility {

    public  static String RP_LOGIN ="login.json";
    public  static String RP_LOGIN_SUCCESS ="loginSuccess.json";
    public  static String RP_PRODUCT ="product.json";
    public  static String RP_USER_EXIST ="userExist.json";
    public  static String RP_REQUEST_PIN ="requestPin.json";


    public static boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        }

        return string.trim().isEmpty();
    }

    /**
     * Returns the device width in px
     *
     * @return Device width
     */
    public static int getDeviceWidthInPX(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * Returns the device height in px
     *
     * @return Device height
     */
    public static int getDeviceHeightInPX(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * Returns the device width in dp
     *
     * @return Device width
     */
    public static float getDeviceWidthInDP(Context context) {
        int width = getDeviceWidthInPX(context);
        return px2dip(width, context);
    }

    /**
     * Returns he device height in dp
     *
     * @return Device height
     */
    public static float getDeviceHeightInDP(Context context) {
        int height = getDeviceHeightInPX(context);
        return px2dip(height, context);
    }

    /**
     * Converts DP to PX
     *
     * @param dp
     * @return pixel
     */

    public static float dip2px(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * Converts PX to DP
     *
     * @param px
     * @param context
     * @return dp
     */

    public static float px2dip(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String twoDecimalPoint(double number) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        return df.format(number);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static long getUnixTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * Provide 16 Digit random String
     *
     * @param
     * @return
     */
    public static String getRandomPassword() {
        String unixTimeStamp = String.valueOf(getUnixTimeStamp());

        StringBuilder randomString = new StringBuilder("asdf%^&" + unixTimeStamp + "ghjk!@#");

        Random random = new Random();
        if (randomString.length() == 10) {

        } else if (randomString.length() > 10) {
            while (randomString.length() > 10) {
                int randomNumber = random.nextInt(randomString.length());
                randomString.deleteCharAt(randomNumber);

            }
        } else if (randomString.length() < 10) {
            for (int i = 0; randomString.toString().length() < 10; i++)
                randomString.append(randomString);

            while (randomString.length() > 10) {
                int randomNumber = random.nextInt(randomString.length());
                randomString.deleteCharAt(randomNumber);
            }
        }
        return randomString.toString();
    }






    static String loadJSONFromAsset(Context context,String RPname) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(RPname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
