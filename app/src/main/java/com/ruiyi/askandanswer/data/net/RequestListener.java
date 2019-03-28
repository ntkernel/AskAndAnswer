/**
 * 
 */
package com.ruiyi.askandanswer.data.net;

/**
 * @author liuz
 * 
 */
public interface RequestListener<T> {
    /**
     * 请求准备
     */
    public void onPreRequest();

    /**
     * 请求完毕
     * @param object 请求返回数据
     */
    public void onAfterRequest(T object);

    /**
     * 请求成功
     * @param response 请求返回数据
     */
    public void onRequestSuccess(T response);

    /**
     * 请求失败
     * @param response 请求返回数据
     */
    public void onRequestFail(String response);

    /**
     * 请求取消
     * @param requestCode 请求标识
     */
    public void onRequestCancel(int requestCode);
}
