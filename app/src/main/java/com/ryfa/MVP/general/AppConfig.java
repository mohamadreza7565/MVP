package com.ryfa.MVP.general;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryfa.MVP.R;
import com.ryfa.MVP.widgets.SnackBar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppConfig {

    public static final int REQUEST_GALLERY_PICTURE = 0x01;
    public static final int REQUEST_CAPTURE_PICTURE = 0x02;
    public static final int REQUEST_VIDEO_RECORD_PICTURE = 0x03;
    OnSearchDoneClickListener onSearchDoneClickListener;
    OnScrollRecyclerView onScrollRecyclerView;
    OnScrollNestedScrollView onScrollNestedScrollView;
    public static final long MIN_ASCII_NUMBER = 48;
    public static final long MAX_ASCII_NUMBER = 57;
    public static final long MIN_ASCII_CAPITAL_ENGLISH_WORD = 65;
    public static final long MAX_ASCII_CAPITAL_ENGLISH_WORD = 90;
    public static final long MIN_ASCII_LOWERCASE_ENGLISH_WORD = 97;
    public static final long MAX_ASCII_LOWERCASE_ENGLISH_WORD = 122;
    public static final long ADSAIN_ASCII_CODE = 64;
    public static final long UNDERLINE_ASCII_CODE = 95;
    public static final long DOT_ASCII_CODE = 46;

    public static final String UP_SCROLL = "UP_SCROLL";
    public static final String DOWN_SCROLL = "DOWN_SCROLL";
    public static final String LEFT_SCROLL = "LEFT_SCROLL";
    public static final String RIGHT_SCROLL = "RIGHT_SCROLL";

    DownloadFile downloadFile;

    public static AppConfig getInstance() {
        return new AppConfig();
    }

    public AppConfig() {
    }


    @SuppressLint("ObsoleteSdkInt")
    public static final void clipboard(Context context, String message, View view) {
        // Toast.makeTEXT(context, "کپی شد");
        SnackBar.make(context, view.findViewById(R.id.layout), context.getResources().getString(R.string.copied_to_clipboard)).showLong();
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(message);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", message);
            clipboard.setPrimaryClip(clip);
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    public static final void call(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    public AppConfig checkScrollRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0) {
                    onScrollRecyclerView.onScrollChanged(RIGHT_SCROLL);
                } else if (dx < 0) {
                    onScrollRecyclerView.onScrollChanged(LEFT_SCROLL);
                } else {
                    // No Horizontal Scrolled
                }

                if (dy > 0) {
                    onScrollRecyclerView.onScrollChanged(DOWN_SCROLL);
                    onScrollRecyclerView.isLastItemScroll(isLastItemDisplaying(recyclerView));
                } else if (dy < 0) {
                    onScrollRecyclerView.onScrollChanged(UP_SCROLL);
                } else {
                    // No Vertical Scrolled
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        // The RecyclerView is not scrolling

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        // Scrolling now

                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        // Scroll Settling
                        break;

                }
            }
        });

        return this;
    }

    public AppConfig checkScrollNestedScrollView(NestedScrollView scrollView) {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    onScrollNestedScrollView.onScrollChanged(DOWN_SCROLL);
                }
                if (scrollY < oldScrollY) {
                    onScrollNestedScrollView.onScrollChanged(UP_SCROLL);
                }
            }
        });

        return this;
    }

    public boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    public interface OnScrollRecyclerView {
        void isLastItemScroll(boolean isLastItem);

        void onScrollChanged(String directionScroll);
    }

    public interface OnScrollNestedScrollView {

        void onScrollChanged(String directionScroll);
    }

    public OnScrollRecyclerView getOnScrollRecyclerView() {
        return onScrollRecyclerView;
    }

    public void setOnScrollRecyclerView(OnScrollRecyclerView onScrollRecyclerView) {
        this.onScrollRecyclerView = onScrollRecyclerView;
    }

    public OnScrollNestedScrollView getOnScrollNestedScrollView() {
        return onScrollNestedScrollView;
    }

    public AppConfig setOnScrollNestedScrollView(OnScrollNestedScrollView onScrollNestedScrollView) {
        this.onScrollNestedScrollView = onScrollNestedScrollView;
        return this;
    }

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final void openBrowser(Context context, String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(browserIntent);
    }

    public static void changeActivity(Context context, String activityAddress) {

        try {
            Class<?> classType = Class.forName(activityAddress);
            context.startActivity(new Intent(context, classType));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void changeActivityForResult(Activity context, String activityAddress, int reqCode) {

        try {
            Class<?> classType = Class.forName(activityAddress);
            context.startActivityForResult(new Intent(context, classType), reqCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void configSoftKeyPad(final EditText search_input, final OnSearchDoneClickListener onSearchDoneClickListener) {

        search_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (search_input.length() > 2)
                        onSearchDoneClickListener.onSearchDoneClick(search_input);
                    //searchRequest(et_search.getText().toString(),user != null ? user.getTabIndex(): -1);
                    return true;
                }
                return true;
            }
        });
    }

    public interface OnSearchDoneClickListener {
        void onSearchDoneClick(EditText search_input);
    }

    public void setOnSearchDoneClickListener(OnSearchDoneClickListener onSearchDoneClickListener) {
        this.onSearchDoneClickListener = onSearchDoneClickListener;
    }

    public static String TodayString() {
        return String.format("%s\n%s", PersianCalendar.getInstance().getPersianWeekDayStr(), PersianCalendar.getInstance().getIranianDate());
    }

    public void openCamera(Activity activity, String packageName) {
        if (!RequestPermission.newInstance(activity, new String[]{RequestPermission.CAMERA}).request())
            return;

        String captureFileName = "IMG_" + new Date().getTime() / 1000 + ".jpg";
        File TMP_DIR = new File(FileManager.APP_TMP_DIR);
        if (!TMP_DIR.exists()) TMP_DIR.mkdirs();
        File TMP_FILE = new File(FileManager.APP_TMP_DIR + captureFileName);
        Uri path = FileProvider.getUriForFile(activity, packageName + ".provider", TMP_FILE);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
        activity.startActivityForResult(intent, REQUEST_CAPTURE_PICTURE);
    }

    public void openGallery(Activity activity) {
        if (!RequestPermission.newInstance(activity, new String[]{RequestPermission.STORAGE}).request())
            return;

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_GALLERY_PICTURE);
    }


    public static void shareText(Context context, String postContent) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, postContent);
        context.startActivity(Intent.createChooser(intent, "Share"));
    }


    public static void shareFile(Context context, File tmpFile, String content, String packageName) {

        Uri path = FileProvider.getUriForFile(context, packageName + ".provider", tmpFile);
        Intent i = new Intent(Intent.ACTION_SEND);
        if (content != null && content.length() > 0)
            i.putExtra(Intent.EXTRA_TEXT, content);
        i.putExtra(Intent.EXTRA_STREAM, path);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setType("plain/*");
        context.startActivity(Intent.createChooser(i, "Share"));

    }

    public static final void downloadImage(Context context, String finalMainImageURL, final DownloadFile downloadFile) {

        Calendar cal = Calendar.getInstance();
        final int filename = (int) (cal.getTimeInMillis() / 1000);
        final String path = context.getExternalCacheDir() + File.separator + "photo";

        Picasso.get().load(finalMainImageURL).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                final Bitmap shareBitmap = overlay(bitmap, R.mipmap.ic_launcher, product);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Environment.getExternalStorageDirectory().getPath() + "/" + url
                        try {
                            String tmpFileName = filename + ".jpg";
                            File TMP_DIR = new File(path);
                            if (!TMP_DIR.exists()) TMP_DIR.mkdirs();
                            File TMP_FILE = new File(path + tmpFileName);
                            TMP_FILE.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(TMP_FILE);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                            downloadFile.onSucsess(TMP_FILE);
                        } catch (IOException e) {
                            //Log.e("IOException", e.getLocalizedMessage());
                            downloadFile.onErorr();
                        }

                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }


            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public static final void shareImage(Context context, File tmpFile, String content) {
        Uri path = FileProvider.getUriForFile(context, getPackageName(context) + ".provider", tmpFile);
        Intent i = new Intent(Intent.ACTION_SEND);
        if (content != null && content.length() > 0)
            i.putExtra(Intent.EXTRA_TEXT, content);
        i.putExtra(Intent.EXTRA_STREAM, path);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setType("image/*");
        context.startActivity(Intent.createChooser(i, "Share"));
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static long convertToAsciiCode(String s) {
        StringBuilder sb = new StringBuilder();
        String ascString = null;
        long asciiInt;
        for (int i = 0; i < s.length(); i++) {
            sb.append((int) s.charAt(i));
            char c = s.charAt(i);
        }
        ascString = sb.toString();
        asciiInt = Long.parseLong(ascString);
        return asciiInt;
    }

    public static boolean emailWordType(String s) {

        long asciiCode = convertToAsciiCode(s);

        if ((MIN_ASCII_NUMBER <= asciiCode && asciiCode <= MAX_ASCII_NUMBER)
                || (MIN_ASCII_CAPITAL_ENGLISH_WORD <= asciiCode && asciiCode <= MAX_ASCII_CAPITAL_ENGLISH_WORD)
                || (MIN_ASCII_LOWERCASE_ENGLISH_WORD <= asciiCode && asciiCode <= MAX_ASCII_LOWERCASE_ENGLISH_WORD)
                || asciiCode == ADSAIN_ASCII_CODE
                || asciiCode == DOT_ASCII_CODE) {
            return true;
        }
        return false;
    }

    public static boolean emojiLock(String s) {

        long asciiCode = convertToAsciiCode(s);

        if ((MIN_ASCII_NUMBER <= asciiCode && asciiCode <= MAX_ASCII_NUMBER)
                || (MIN_ASCII_CAPITAL_ENGLISH_WORD <= asciiCode && asciiCode <= MAX_ASCII_CAPITAL_ENGLISH_WORD)
                || (MIN_ASCII_LOWERCASE_ENGLISH_WORD <= asciiCode && asciiCode <= MAX_ASCII_LOWERCASE_ENGLISH_WORD)
                || asciiCode == ADSAIN_ASCII_CODE
                || asciiCode == UNDERLINE_ASCII_CODE
                || asciiCode == DOT_ASCII_CODE) {
            return true;
        }
        return false;
    }

    public static void clearFormEditText(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearFormEditText((ViewGroup) view);
        }
    }

    public static ArrayList<EditText> getAllEditText(ViewGroup group, ArrayList<EditText> list) {

        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                list.add((EditText) view);
            }
            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                getAllEditText((ViewGroup) view, list);
        }

        return list;
    }

    public static void clearFormBtn(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof Button) {
                ((Button) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearFormBtn((ViewGroup) view);
        }
    }

    public static void clearFormTextView(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearFormTextView((ViewGroup) view);
        }
    }

    public static void clearAllForm(ViewGroup group) {
        clearFormEditText(group);
        clearFormBtn(group);
        clearFormTextView(group);
    }

    public static final int getVetsionCode(Context context) {
        int v = 0;
        try {
            v = context.getPackageManager().getPackageInfo(getPackageName(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return v;
    }

    public static final String getVetsionName(Context context) {
        String version = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static final String getPackageName(Context context) {
        String packageName = context.getPackageName();
        return packageName;
    }

    public static final void setLocale(Activity context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        SharedPrefHandler.setStringPreference(context, SharedPrefHandler.LANGUAGE, lang);
    }

    public static final String getLocale(Context context) {
        return SharedPrefHandler.getStringPreference(context, SharedPrefHandler.LANGUAGE, "fa");
    }

    public interface DownloadFile {
        void onSucsess(File file);

        void onErorr();
    }

    public static String splitDigits(Double number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols();
            decimalFormatSymbol.setGroupingSeparator(',');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbol);
            return decimalFormat.format(number);
        } catch (Exception ex) {
            return String.valueOf(number);
        }
    }


}



