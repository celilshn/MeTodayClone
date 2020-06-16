package com.cengcelil.metodayclone.Model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GalleryHelper {
    private static final String TAG = "GalleryHelper";
    private ArrayList<String> allImagePath;
    private ArrayList<String> matchedImages;
    private Context context;
    private String[] projection = {MediaStore.MediaColumns.DATA};

    public GalleryHelper(Context context) {
        this.context = context;
    }

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

    public ArrayList<String> getMatchedImages() {
        matchedImages = new ArrayList<>();
        Calendar currentCalendar = Calendar.getInstance();
        Calendar tempCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date(System.currentTimeMillis()));
        for (String filepath : getAllImagesPath()) {
            File file = new File(filepath);
            if (file.exists()) {
                tempCalendar.setTime(new Date(file.lastModified()));
                if(currentCalendar.get(Calendar.DAY_OF_MONTH) == tempCalendar.get(Calendar.DAY_OF_MONTH))
                    if(currentCalendar.get(Calendar.MONTH) == tempCalendar.get(Calendar.MONTH))
                        if(currentCalendar.get(Calendar.YEAR) != tempCalendar.get(Calendar.YEAR))
                            matchedImages.add(file.getName());

            }
        }

        return matchedImages;
    }
}
