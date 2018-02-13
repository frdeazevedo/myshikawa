package br.fazevedo.myshikawa;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.fazevedo.myshikawa.db.entity.Shikawa;
import br.fazevedo.myshikawa.list.ShikawaListViewModel;
import br.fazevedo.myshikawa.list.ShikawasAdapter;
import br.fazevedo.myshikawa.list.ShikawasViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton mFAB;
    @BindView(R.id.rv_shikawas)
    RecyclerView mRVShikawas;

    private ShikawaListViewModel mShikawasListViewModel;
    private ShikawasAdapter mShikawasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mShikawasAdapter = new ShikawasAdapter(new ArrayList<Shikawa>(), new ShikawasViewHolder.ShikawaListener() {
            @Override
            public void onShikawaClick(Shikawa shikawa) {
                //TODO
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRVShikawas.setLayoutManager(llm);
        mRVShikawas.setHasFixedSize(true);
        mRVShikawas.setAdapter(mShikawasAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                llm.getOrientation());
        mRVShikawas.addItemDecoration(dividerItemDecoration);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShikawasListViewModel.addShikawa(
                        new Shikawa("Shikawa Title", "This is a test Shikawa!"));
            }
        });

        mShikawasListViewModel = ViewModelProviders.of(this).get(ShikawaListViewModel.class);
        mShikawasListViewModel.getShikawasList().observe(this, new Observer<List<Shikawa>>() {
            @Override
            public void onChanged(@Nullable List<Shikawa> shikawas) {
                mShikawasAdapter.setShikawasList(shikawas);
            }
        });
    }
}
