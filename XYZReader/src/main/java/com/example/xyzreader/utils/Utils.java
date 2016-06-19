package com.example.xyzreader.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chris on 6/18/16.
 */
public class Utils {

    /*
* Bitmap.CompressFormat can be PNG,JPEG or WEBP.
*
* quality goes from 1 to 100. (Percentage).
*
* dir you can get from many places like Environment.getExternalStorageDirectory() or mContext.getFilesDir()
* depending on where you want to save the image.
*/

    public static boolean saveBitmapToFile(Context context, String fileName, Bitmap bm) {
        File dir = new File(context.getFilesDir() + File.separator + "drawable");
        if (!dir.exists()) dir.mkdir();

        File imageFile = new File(dir,fileName);

        Log.d("Utils","saving bitmap to:" + imageFile.getPath());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);

            bm.compress(Bitmap.CompressFormat.PNG,100,fos);

            fos.close();

            return true;
        }
        catch (IOException e) {
            Log.e("app",e.getMessage());
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    public static Bitmap getBitmapFromFile(Context context, String fileName) {
        File dir = new File(context.getFilesDir() + File.separator + "drawable");
        File imageFile = new File(dir,fileName);
        Log.d("Utils","getting bitmap from:" + imageFile.getPath());

        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
        return bitmap;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
