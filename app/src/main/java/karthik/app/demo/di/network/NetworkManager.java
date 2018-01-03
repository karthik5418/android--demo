package karthik.app.demo.di.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import karthik.app.demo.di.scope.ApplicationContext;

/**
 * Created by NiCK on 1/2/2018.
 */

@Singleton
public class NetworkManager {

    private Context context;

    @Inject
    public NetworkManager(@ApplicationContext Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
