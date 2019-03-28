/**
 * 
 */
package com.ruiyi.askandanswer.data.net;

/**
 * @author liuz
 *
 */
public abstract class DefaultRequestListener<T> implements RequestListener<T> {
    @Override
    public void onPreRequest() {

    }

    @Override
    public void onAfterRequest(T object) {

    }

    @Override
    public void onRequestCancel(int requestCode) {

    }

}
