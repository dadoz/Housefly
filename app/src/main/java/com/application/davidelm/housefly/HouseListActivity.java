package com.application.davidelm.housefly;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.application.davidelm.housefly.adapter.HouseListAdapter;
import com.application.davidelm.housefly.application.HouseflyApplication;
import com.application.davidelm.housefly.presenter.HousePresenter;
import com.application.davidelm.housefly.presenter.HouseView;
import com.application.davidelm.housefly.utils.Utils;
import com.application.davidelm.housefly.views.EmptyView;
import com.application.subitoit.githubstargazers.R;

import java.lang.ref.WeakReference;
import java.util.List;


public class HouseListActivity extends AppCompatActivity implements HouseView {
    private String owner;
    private String repo;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    EmptyView emptyView;

    private HousePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stargazers_list);

        repo = ((HouseflyApplication) getApplication()).getRepo();
        owner = ((HouseflyApplication) getApplication()).getOwner();

        bindView();
        onInitView();
    }

    /**
     *
     */
    private void bindView() {
        recyclerView = (RecyclerView) findViewById(R.id.stargazerRecyclerViewId);
        progressBar = (ProgressBar) findViewById(R.id.stargazerProgressbarId);
        emptyView = (EmptyView) findViewById(R.id.emptyViewId);
    }

    /**
     * iit view and retrieve stargazers data
     */
    private void onInitView() {
        initActionbar();
        presenter = new HousePresenter();
        presenter.init(new WeakReference<>(this),
                new WeakReference<>(this), Utils.buildParams(owner, repo));
    }

    /**
     * actionbar set listener and back arrow
     */
    private void initActionbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void bindData(List<?> items, int i) {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        initRecyclerView(items);
    }


    @Override
    public void onRetrieveDataError(String error) {
        //TODO implement it - show a view maybe
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        Snackbar.make(findViewById(R.id.activity_main), R.string.retrieve_error,
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * init recycler view binding data by adapter
     * @param items
     */
    private void initRecyclerView(List<?> items) {
        if (items.size() == 0) {
            return;
        }
        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new HouseListAdapter(items));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (presenter != null)
                    presenter.unsubscribe();
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
