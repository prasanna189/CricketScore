package com.example.android.cricketscore;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

public class FeedbackUtils {
    private static final String FEEDBACK_CHOOSER_TITLE = "Thank you for your feedback!";
    private static final String EMAIL_ADDRESS = "spkforwork@gmail.com";

    public static void askForFeedback(Context context) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, getFeedbackEmailAddress());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getFeedbackEmailSubject(context));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getFeedbackDeviceInformation(context));
        context.startActivity(Intent.createChooser(emailIntent, FEEDBACK_CHOOSER_TITLE));
    }

    private static String[] getFeedbackEmailAddress() {
        return new String[] { EMAIL_ADDRESS };
    }

    private static String getFeedbackEmailSubject(Context context) {
        return getApplicationName(context) + " Feedback v" + getAppVersion(context);
    }

    private static String getApplicationName(Context context) {
        return context.getString(context.getApplicationInfo().labelRes);
    }

    private static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "vX.XX";
        }
    }

    private static String getFeedbackDeviceInformation(Context context) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("Write here : ");
        emailMessage.append("\n\n\n_________________");
        emailMessage.append("\n\nPlease don't delete this data. This data will help with app issues.");
        emailMessage.append("\n\nDevice info:\n\n");
        emailMessage.append(getHandsetInformation(context));
        emailMessage.append("_________________\n\n");
        return emailMessage.toString();
    }

    private static String getHandsetInformation(Context context) {
        StringBuilder handsetInfoBuilder = new StringBuilder();

        handsetInfoBuilder.append("Bootloader: " + Build.BOOTLOADER);
        handsetInfoBuilder.append("\nBrand: " + Build.BRAND);
        handsetInfoBuilder.append("\nDevice: " + Build.DEVICE);
        handsetInfoBuilder.append("\nManufacturer, Model: " + Build.MANUFACTURER+" "+ Build.MODEL);
        handsetInfoBuilder.append("\nScreen Density: " + getDeviceDensity(context));
        handsetInfoBuilder.append("\nVersion, SDK:" + Build.VERSION.RELEASE+" , "+ Build.VERSION.SDK_INT);
        handsetInfoBuilder.append("\nCodename, Incremental: " + Build.VERSION.CODENAME+" , "+ Build.VERSION.INCREMENTAL);
        handsetInfoBuilder.append("\nBuild: " + Build.DISPLAY);
        handsetInfoBuilder.append("\n");
        return handsetInfoBuilder.toString();
    }

    private static float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}