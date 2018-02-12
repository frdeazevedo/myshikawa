package br.fazevedo.myshikawa;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.fazevedo.myshikawa.db.entity.Shikawa;
import br.fazevedo.myshikawa.list.ShikawaListViewModel;
import br.fazevedo.myshikawa.list.ShikawasAdapter;
import br.fazevedo.myshikawa.list.ShikawasViewHolder;

public class MainActivity extends AppCompatActivity {

    private ShikawaListViewModel mShikawasListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShikawasAdapter adapter = new ShikawasAdapter(new ArrayList<Shikawa>(), new ShikawasViewHolder.ShikawaListener() {
            @Override
            public void onShikawaClick(Shikawa shikawa) {
                //TODO
            }
        });
        RecyclerView rv = findViewById(R.id.rv_shikawas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        mShikawasListViewModel = ViewModelProviders.of(this).get(ShikawaListViewModel.class);
        mShikawasListViewModel.getShikawasList().observe(this, new Observer<List<Shikawa>>() {
            @Override
            public void onChanged(@Nullable List<Shikawa> shikawas) {
                adapter.setShikawasList(shikawas);
            }
        });
    }
}
