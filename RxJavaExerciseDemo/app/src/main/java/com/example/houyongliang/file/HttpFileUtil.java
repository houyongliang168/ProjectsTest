package com.example.houyongliang.file;


import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by houyongliang on 2018/1/24.
 */

public class HttpFileUtil extends HttpCommon {
    private String mUrl;
    private String mFolder;
    private String mFilename;

    private long mContentLength;
    private long mDownloadingSize;
    private boolean mNeedProgress;//是否展示 文件大小
    private UrlContentLength urlContentLength;

    public HttpFileUtil(String url, boolean needProgress) {
        mUrl = url;
        mNeedProgress = needProgress;
    }

    public void setTheUrlContentLength(UrlContentLength urlContentLength) {
        this.urlContentLength = urlContentLength;
    }

    @Override
    public void run() {


        HttpURLConnection client = null;
        InputStream responseIs = null;
        FileOutputStream fos = null;
        int statusCode = -1;
        boolean success = false;
        Exception failException = null;

        try {

            client = (HttpURLConnection) new URL(mUrl).openConnection();

            // 设置网络超时参数
            client.setConnectTimeout(TIMEOUT);
            client.setReadTimeout(TIMEOUT);
            client.setDoInput(true);
            client.setRequestMethod("GET");

            statusCode = client.getResponseCode();
            success = client.getResponseCode() == HttpURLConnection.HTTP_OK;

            if (success) {
                if (mNeedProgress) {
                    mContentLength = client.getContentLength();

                    if (urlContentLength != null) {
                        urlContentLength.getUrlContentLength(mContentLength);
                    }

                    return;

                }
            } else {
                mContentLength = 0;
                urlContentLength.getUrlContentLength(mContentLength);
                return;
            }
        } catch (Exception e) {
            failException = e;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (responseIs != null) {
                    responseIs.close();
                }
                if (client != null) {
                    client.disconnect();
                }

            } catch (IOException e) {

            }
        }
    }

    public interface UrlContentLength {
        void getUrlContentLength(long mContentLength);
    }

    public static void main(String[] args) {
        HttpFileUtil httpFileUtil = new HttpFileUtil("http://1253804083.vod2.myqcloud.com/37128721vodgzp1253804083/be2db07d4564972818934883834/5Z3tOaKEDDwA.mp4", true);
        httpFileUtil.setTheUrlContentLength(new HttpFileUtil.UrlContentLength() {
            @Override
            public void getUrlContentLength(long mContentLength) {
                Log.e("adf",mContentLength+"");
                Log.e("adf", FormatUtil.sizeFormatNum2String(mContentLength) + "流量");
                System.out.println(mContentLength);
                System.out.println(FormatUtil.sizeFormatNum2String(mContentLength) + "流量");
            }
        });
        new Thread(httpFileUtil).start();

    }
}
