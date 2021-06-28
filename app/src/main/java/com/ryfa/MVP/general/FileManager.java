package com.ryfa.MVP.general;

import android.os.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMz on 9/5/2016.
 */
public class FileManager {

    public static final String SD_CARD = Environment.getExternalStorageDirectory().getPath();
    public static final String APP_FOLDER = "/application/";
    public static final String APP_TMP_FOLDER = "tmp/";
    public static final String APP_GIF_FOLDER = "gif/";
    public static final String APP_PDF_FOLDER = "pdf/";
    public static final String APP_JPG_FOLDER = "jpg/";
    public static final String APP_DIR = SD_CARD + APP_FOLDER;
    public static final String APP_TMP_DIR = APP_DIR + APP_TMP_FOLDER;
    public static final String APP_GIF_DIR = APP_DIR + APP_GIF_FOLDER;
    public static final String APP_PDF_DIR = APP_DIR + APP_PDF_FOLDER;
    public static final String APP_JPG_DIR = APP_DIR + APP_JPG_FOLDER;

    public static final Map<String, String> appDirs() {
        Map<String, String> dirs = new HashMap<String, String>();
        dirs.put("pdf", APP_DIR);
        dirs.put("gif", APP_GIF_DIR);
        dirs.put("jpg", APP_JPG_DIR);
        return dirs;
    }

    public FileManager() {

    }

//    public void downloadImage(Context context, String imageURL, final OnResultDownloadManager onResultDownloadManager) {
//
//        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance(false, null);
//        onResultDownloadManager.onStart(loadingDialogFragment);
//
//        Picasso.get().load(imageURL).into(new Target() {
//            @Override
//            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
////                final Bitmap shareBitmap = overlay(bitmap, R.mipmap.ic_launcher, product);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Environment.getExternalStorageDirectory().getPath() + "/" + url
//                        try {
//                            String tmpFileName = new Date().getTime() / 1000 + ".jpg";
//                            File TMP_DIR = new File(APP_JPG_DIR);
//                            if (!TMP_DIR.exists()) TMP_DIR.mkdirs();
//                            File TMP_FILE = new File(APP_JPG_DIR + tmpFileName);
//                            TMP_FILE.createNewFile();
//                            FileOutputStream ostream = new FileOutputStream(TMP_FILE);
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
//                            ostream.flush();
//                            ostream.close();
//                            onResultDownloadManager.onResult(TMP_FILE);
//                        } catch (IOException e) {
//                            //Log.e("IOException", e.getLocalizedMessage());
//                            onResultDownloadManager.onError();
//                        }
//
//                    }
//                }).start();
//
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//                onResultDownloadManager.onError();
//            }
//
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });
//
//        onResultDownloadManager.onFinish(loadingDialogFragment);
//    }

//    public interface OnResultDownloadManager {
//        void onStart(LoadingDialogFragment loading);
//
//        void onFinish(LoadingDialogFragment loading);
//
//        void onResult(File tempFile);
//
//        void onError();
//    }

}
