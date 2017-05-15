package com.owncloud.android.ui.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.owncloud.android.R;
import com.owncloud.android.lib.common.OwnCloudClient;
import com.owncloud.android.lib.common.OwnCloudClientFactory;
import com.owncloud.android.lib.common.OwnCloudCredentialsFactory;
import com.owncloud.android.lib.common.network.OnDatatransferProgressListener;
import com.owncloud.android.lib.common.operations.OnRemoteOperationListener;
import com.owncloud.android.lib.common.operations.RemoteOperation;
import com.owncloud.android.lib.common.operations.RemoteOperationResult;
import com.owncloud.android.lib.common.utils.Log_OC;
import com.owncloud.android.lib.resources.files.FileUtils;
import com.owncloud.android.lib.resources.files.ReadRemoteFolderOperation;
import com.owncloud.android.lib.resources.files.RemoteFile;

/**
 * Created by Nygama on 2017/5/12.
 */

public class SearchPicsActivity extends Activity implements OnRemoteOperationListener ,OnDatatransferProgressListener{
    private Handler mHandler;
    private OwnCloudClient mClient;
    private ImageView imageView;
    private  String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_pics);
        imageView = (ImageView) findViewById(R.id.image_view_glide_test);

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_action_download)
                .into(imageView);
 /*
        mHandler = new Handler();

        Uri serverUri = Uri.parse("http://172.17.226.119:8080/owncloud");
        mClient = OwnCloudClientFactory.createOwnCloudClient(serverUri, this, true);
        mClient.setCredentials(
                OwnCloudCredentialsFactory.newBasicCredentials(
                        "admin",
                        "admin"
                )
        );
*/
       // startRefresh();
      //  Log.v("11","222");

    }

    private void startRefresh() {
        ReadRemoteFolderOperation refreshOperation = new ReadRemoteFolderOperation(FileUtils.PATH_SEPARATOR);
        refreshOperation.execute(mClient, this, mHandler);
    }


    @Override
    public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {


        if (!result.isSuccess()) {
            Toast.makeText(this, "#######11111", Toast.LENGTH_SHORT).show();
           // Log_OC.e(result.getLogMessage(), result.getException());

        } else if (operation instanceof ReadRemoteFolderOperation) {
        //    onSuccessfulRefresh((ReadRemoteFolderOperation)operation, result);


        } else {
            Toast.makeText(this,"####22222222", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onTransferProgress(long progressRate, long totalTransferredSoFar, long totalToTransfer, String fileAbsoluteName) {

    }
/*
    public void loadImage(View view) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_action_download)
                .into(imageView);
    }
    */
}
