package io.github.yedaxia.richeditor;

import android.net.Uri;

/**
 * 上传本地图片
 * @author Darcy https://yedaxia.github.io/
 * @version 2017/5/9.
 */

public interface IUploadEngine {

    int STATUS_UPLOAD_INITIAL = 0;
    int STATUS_UPLOAD_START = 1;
    int STATUS_UPLOADING = 2;
    int STATUS_UPLOAD_SUCCESS = 3;
    int STATUS_UPLOAD_FAIL = 4;

    /**
     * 上传图片
     * @param imagePath
     * @param listener
     */
    void uploadImage(String imagePath, UploadProgressListener listener);

    /**
     * 取消上传
     * @param imagePath
     */
    void cancelUpload(String imagePath);

    interface UploadProgressListener{

        /**
         * 上传进度回调
         * @param imagePath
         * @param progress
         */
        void onUploadProgress(String imagePath, int progress);

        /**
         * 上传成功回调
         * @param imagePath
         * @param url
         */
        void onUploadSuccess(String imagePath, String url, int width, int height);

        /**
         * 上传失败回调
         * @param imagePath
         * @param errorMsg
         */
        void onUploadFail(String imagePath, String errorMsg);
    }
}
