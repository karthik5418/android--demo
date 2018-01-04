package karthik.app.demo.ui.snapdeal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import dagger.android.AndroidInjection;
import karthik.app.demo.R;
import karthik.app.demo.constants.AppConstants;
import karthik.app.demo.data.adapter.ProductsAdapter;
import karthik.app.demo.data.model.ProductModel;
import karthik.app.demo.viewmodel.ViewModelProviderFactory;

/**
 * Created by NiCK on 1/2/2018.
 */

public class SnapdealSearchActivity extends AppCompatActivity {

    private static final String TAG = "SnapdealSearchActivity";


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.sv_sort)
    Spinner svSort;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.pr_dialog)
    ProgressBar pgLoader;


    @Inject
    ViewModelProviderFactory viewFactory;

    SnapdealViewModel snapdealViewModel;


    private List<ProductModel> list;
    private ProductModel model;
    private ProductsAdapter adapter;


    private String mSorting = "";
    private ArrayList<String> sortingList;
    private ArrayAdapter<String> sortingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapdeal);
        ButterKnife.bind(this);
        initView();
        initViewModel();
        initData();
    }


    private void initView() {
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {


        etSearch.setText("mobile");

        sortingList = new ArrayList<>(6);
        sortingList.add(AppConstants.RELEVANCE);
        sortingList.add(AppConstants.POPULARITY);
        sortingList.add(AppConstants.PRICE_LOW_TO_HIGH);
        sortingList.add(AppConstants.PRICE_HIGH_TO_LOW);
        sortingList.add(AppConstants.DISCOUNT);
        sortingList.add(AppConstants.FRESH_ARRIVALS);

        sortingAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sortingList);
        sortingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        svSort.setAdapter(sortingAdapter);

        observeLiveData(etSearch.getText().toString());

    }

    private void initViewModel() {
        snapdealViewModel = ViewModelProviders.of(this, viewFactory).get(SnapdealViewModel.class);
    }

    @OnTextChanged(R.id.et_search)
    public void searchProduct() {
        int charLength = etSearch.getText().length();
        if (charLength >= 3) {
            observeLiveData(etSearch.getText().toString());
        }
    }

    private void observeLiveData(String keyword) {


        pgLoader.setVisibility(View.VISIBLE);
        rvProducts.setVisibility(View.GONE);

        snapdealViewModel.searchProduct(keyword, mSorting).observe(this, new Observer<Document>() {
            @Override
            public void onChanged(@Nullable Document document) {

                pgLoader.setVisibility(View.GONE);
                rvProducts.setVisibility(View.VISIBLE);


                try {

                    Elements elementsThumbnail = document.select("div.product-tuple-image");

                    list = new ArrayList<>(elementsThumbnail.size());


                    for (Element element : elementsThumbnail) {
                        String thumbnail = "";
                         thumbnail = element.select("img.product-image ").first().attr("src");
                         if (thumbnail.equals("") || thumbnail.isEmpty()){
                             try {
                                 thumbnail = element.select("img.product-image lazy-load").select("data").first().attr("src");
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                         }

                        model = new ProductModel();
                        model.setThumbnail(thumbnail);
                        list.add(model);
                    }

                    Elements elementsDesc = document.select("div.product-tuple-description");


                    for (int i = 0; i < elementsDesc.size(); i++) {

                        //String link = elementsDesc.get(i).select("a.dp-widget-link").first().attr("href");
                        String title = elementsDesc.get(i).select("p.product-title").first().attr("title");
                        String price = elementsDesc.get(i).select("span.product-price").first().text();


                        list.get(i).setName(title);
                        list.get(i).setPrice(price);

                    }


                    adapter = new ProductsAdapter(SnapdealSearchActivity.this, list);
                    rvProducts.setAdapter(adapter);

                    Log.d(TAG, "onChanged: " + list.toString());


                } catch (Exception e) {

                }
            }
        });
    }


    @OnItemSelected(R.id.sv_sort)
    public void spinnerItemSelect(Spinner spinner, int position) {
        switch (position) {
            case 0:
                mSorting = AppConstants.RELEVANCE_VALUE;
                break;

            case 1:
                mSorting = AppConstants.POPULARITY_VALUE;
                break;
            case 2:
                mSorting = AppConstants.PRICE_LOW_TO_HIGH_VALUE;
                break;
            case 3:
                mSorting = AppConstants.PRICE_HIGH_TO_LOW_VALUE;
                break;
            case 4:
                mSorting = AppConstants.DISCOUNT_VALUE;
                break;

            default:
                mSorting = AppConstants.FRESH_ARRIVALS_VALUE;
        }

        observeLiveData(etSearch.getText().toString());
    }
}
