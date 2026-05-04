#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_iago_currencyhashconverter_MainActivity_generateHash(
        JNIEnv* env,
        jobject /* this */,
        jstring input) {

    const char* str = env->GetStringUTFChars(input, nullptr);

    // Algoritmo de hash simples (djb2)
    unsigned long hash = 5381;
    int c;
    const char* ptr = str;
    while ((c = *ptr++)) {
        hash = ((hash << 5) + hash) + c;
    }

    env->ReleaseStringUTFChars(input, str);

    return env->NewStringUTF(std::to_string(hash).c_str());
}