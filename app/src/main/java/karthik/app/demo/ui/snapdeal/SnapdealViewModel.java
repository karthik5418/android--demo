package karthik.app.demo.ui.snapdeal;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.jsoup.nodes.Document;

import javax.inject.Inject;

import karthik.app.demo.data.repository.SnapdealSearchRepository;

/**
 * Created by NiCK on 1/3/2018.
 */

public class SnapdealViewModel extends ViewModel {

    private SnapdealSearchRepository repository;
    private MutableLiveData<Document> liveData;


    @Inject
    public SnapdealViewModel(SnapdealSearchRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Document> searchProduct(String keyword, String sorting) {

        return liveData = repository.getSearchList(keyword, sorting);
    }

    public MutableLiveData<Document> getProduct() {
        return liveData;
    }

}
