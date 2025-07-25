package com.book.studio.pngquant;

public class PngquantBridge {

    static {
        System.loadLibrary("pngquant");
    }

    public static native int compressPng(String inputPath, String outputPath);
}
