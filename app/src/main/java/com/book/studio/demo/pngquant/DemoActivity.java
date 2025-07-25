package com.book.studio.demo.pngquant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.book.studio.pngquant.PngquantBridge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DemoActivity extends AppCompatActivity {

    private String outImagePath;
    private String inputPath;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_layout);
        imageView = findViewById(R.id.image);
        outImagePath = getFilesDir().getAbsolutePath() + "/out_test.png";
        inputPath = getFilesDir().getAbsolutePath() + "/in_test.png";
    }

    private void copyAssetsFile(Context context, String fileName, String destFilePath) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            myOutput = new FileOutputStream(destFilePath);
            myInput = context.getAssets().open(fileName);
            byte[] buffer = new byte[1024];
            int length = myInput.read(buffer);
            while (length > 0) {
                myOutput.write(buffer, 0, length);
                length = myInput.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != myInput) {
                    myInput.close();
                }
                if (null != myOutput) {
                    myOutput.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onToPngClick(View view) {
        File file = new File(inputPath);
        if (!file.isFile() || !file.exists()) {
            copyAssetsFile(this, "test.png", inputPath);
        }
        int result = PngquantBridge.compressPng(inputPath, outImagePath);
        if (result == 0) {
            Toast.makeText(this, "压缩成功", Toast.LENGTH_SHORT);
            Bitmap bitmap = BitmapFactory.decodeFile(outImagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "压缩失败", Toast.LENGTH_SHORT);
        }
    }
}
