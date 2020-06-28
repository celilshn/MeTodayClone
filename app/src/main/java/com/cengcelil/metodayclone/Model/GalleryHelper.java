package com.cengcelil.metodayclone.Model;

import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GalleryHelper {
    private static final String TAG = "GalleryHelper";
    private ArrayList<String> allImagePath;
    private ArrayList<MyImage> matchedImages;
    private Context context;
    private String[] projection = {MediaStore.MediaColumns.DATA};
    private ExifInterface exifInterface;

    public GalleryHelper(Context context) {
        this.context = context;
    }

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    public ArrayList<String> getAllImagesPath() {
        Log.d(TAG, "getAllImagesPath: Tüm imajların listesi alınacak.");
        allImagePath = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(projection[0]);
            while (cursor.moveToNext()) {
                allImagePath.add(cursor.getString(columnIndex));
            }
            cursor.close();
        }
        return allImagePath;
    }

    public ArrayList<MyImage> getMatchedImages() {
        matchedImages = new ArrayList<>();
        Calendar currentCalendar = Calendar.getInstance();
        Calendar tempCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date(System.currentTimeMillis()));
        for (String filepath : getAllImagesPath()) {
            File file = new File(filepath);
            if (file.exists()) {
                try {
                    exifInterface = new ExifInterface(file.getPath());
                    String datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                    if (datetime != null) {
                        try {
                            tempCalendar.setTime(SIMPLE_DATE_FORMAT.parse(datetime));
                            if (currentCalendar.get(Calendar.DAY_OF_MONTH) == tempCalendar.get(Calendar.DAY_OF_MONTH))
                                if (currentCalendar.get(Calendar.MONTH) == tempCalendar.get(Calendar.MONTH))
                                    if (currentCalendar.get(Calendar.YEAR) != tempCalendar.get(Calendar.YEAR))
                                        matchedImages.add(new MyImage(tempCalendar,file,null));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        return matchedImages;
    }
}
