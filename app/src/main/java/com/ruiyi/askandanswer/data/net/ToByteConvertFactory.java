package com.ruiyi.askandanswer.data.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ToByteConvertFactory extends Converter.Factory{
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");
    private static final String TAG = "ToByteConvertFactory";
    private Gson gson;

    public ToByteConvertFactory(){
        gson = new Gson();
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Log.e(TAG, "convert: Converter<?, RequestBody>000" +type+"    "+byte[].class);
        if("byte[]".equals(type+"")){
            return new Converter<ResponseBody, byte[]>() {
                @Override
                public byte[] convert(ResponseBody value) throws IOException {
                    Log.e(TAG, "convert: Converter<ResponseBody, ?>" );
                    return value.bytes();
                }
            };
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}
