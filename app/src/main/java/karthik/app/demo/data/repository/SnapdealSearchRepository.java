package karthik.app.demo.data.repository;

import android.arch.lifecycle.MutableLiveData;

import org.jsoup.nodes.Document;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import karthik.app.demo.data.api.ApiService;

/**
 * Created by NiCK on 1/3/2018.
 */

public class SnapdealSearchRepository {

    private static final String TAG = "SnapdealSearchRepositor";
    private ApiService apiService;
    private MutableLiveData<Document> liveData = new MutableLiveData<>();

    @Inject

    public SnapdealSearchRepository(ApiService apiService) {
        this.apiService = apiService;
    }


    public MutableLiveData<Document> getSearchList(String keyword, String sorting) {

        Observable<Document> observable = apiService.getSearchList(keyword, sorting);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Document>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Document responseBodyResponse) {

                        liveData.setValue(responseBodyResponse);
                        //Log.d(TAG,responseBodyResponse.body().toString());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;

    }
}
