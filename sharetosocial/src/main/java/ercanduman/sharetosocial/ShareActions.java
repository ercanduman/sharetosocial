package ercanduman.sharetosocial;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

public class ShareActions {
    public static void shareAppToGeneral(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(Configuration.INTENT_TYPE_TEXT);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        String shareUrl = Configuration.PLAY_WEB_PREFIX_APP + context.getPackageName();
        intent.putExtra(Intent.EXTRA_TEXT, shareUrl);
        context.startActivity(Intent.createChooser(intent, "Share to"));
    }

    public static void shareAppToFacebook(Context context) {
        String shareAppSubString = initCapAppPackage(Configuration.FACEBOOK_PACKAGE_NAME, 8);
        shareApp(context, Configuration.FACEBOOK_PACKAGE_NAME, shareAppSubString, Configuration.FACEBOOK_WEB_SHARE_URL);
    }

    public static void shareAppToTwitter(Context context) {
        String shareAppSubString = initCapAppPackage(Configuration.TWITTER_PACKAGE_NAME, 7);
        shareApp(context, Configuration.TWITTER_PACKAGE_NAME, shareAppSubString, Configuration.TWITTER_WEB_SHARE_URL);
    }

    public static void showDeveloperApps(Context context, String developerId) {
        if (developerId == null || developerId.equals("") || developerId.isEmpty() || developerId.trim().length() == 0)
            developerId = Configuration.DEFAULT_DEVELOPER_ID;
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Configuration.PLAY_MARKET_DEVELOPER + developerId)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Configuration.PLAY_WEB_PREFIX_DEVELOPER + developerId)));
        }
    }

    public static void showSecondApp(Context context, String appPackageId) {
        if (appPackageId == null || appPackageId.equals("") || appPackageId.isEmpty() || appPackageId.trim().length() == 0)
            appPackageId = Configuration.DEFAULT_SECOND_APP_ID;
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Configuration.PLAY_MARKET_APP + appPackageId)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Configuration.PLAY_WEB_PREFIX_APP + appPackageId)));
        }
    }

    private static void showMessage(Context context, String dialogMessage) {
        if (context == null) return;
        Toast toast = Toast.makeText(context, dialogMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, -35);
        toast.show();
    }

    private static boolean isSocialAppInstalled(String packageName, Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String initCapAppPackage(String socialAppPackageName, int length) {
        socialAppPackageName = socialAppPackageName.toLowerCase();
        String socialAppPackageNameSub = socialAppPackageName.substring(4, length + 4);// all social app's packages start with "com."
        socialAppPackageNameSub = socialAppPackageNameSub.substring(0, 1).toUpperCase() + socialAppPackageNameSub.substring(1);
        return socialAppPackageNameSub;
    }

    private static void shareApp(Context context, String socialAppPackageName, String socialAppPackageNameSubs, String socialAppPackageWebUrl) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType(Configuration.INTENT_TYPE_TEXT);
        if (isSocialAppInstalled(socialAppPackageName, context)) {
            String shareUrl = Configuration.PLAY_WEB_PREFIX_APP + context.getPackageName();
            shareIntent.setPackage(socialAppPackageName);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
            context.startActivity(shareIntent);
        } else {
            showMessage(context, socialAppPackageNameSubs + Configuration.PACKAGE_NOT_INSTALLED);
            String shareUrl = socialAppPackageWebUrl + Configuration.PLAY_WEB_PREFIX_APP + context.getPackageName();
            shareIntent = new Intent(Intent.ACTION_VIEW);
            shareIntent.setData(Uri.parse(shareUrl));
            context.startActivity(shareIntent);
        }
    }
}
