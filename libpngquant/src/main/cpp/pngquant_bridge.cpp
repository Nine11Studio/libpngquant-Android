#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" {
    int pngquant_main(int argc, char *argv[]);
}

#define LOG_TAG "PNGQUANT_JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jint JNICALL
Java_com_book_studio_pngquant_PngquantBridge_compressPng(JNIEnv *env, jobject,
                                                     jstring inputPath_,
                                                     jstring outputPath_) {
    const char *inputPath = env->GetStringUTFChars(inputPath_, 0);
    const char *outputPath = env->GetStringUTFChars(outputPath_, 0);

    LOGI("Compressing PNG from %s to %s", inputPath, outputPath);

    // 构造命令行参数
    char *argv[] = {
            (char *)"pngquant",
            (char *)"--quality=65-80",
            (char *)"--force",
            (char *)"--output", (char *)outputPath,
            (char *)inputPath
    };
    int argc = sizeof(argv) / sizeof(argv[0]);

    int result = pngquant_main(argc, argv);
    LOGI("pngquant_main finished with code %d", result);

    env->ReleaseStringUTFChars(inputPath_, inputPath);
    env->ReleaseStringUTFChars(outputPath_, outputPath);

    return result;
}
