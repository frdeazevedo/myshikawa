package br.fazevedo.myshikawa.screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.ShikawaUtils;
import br.fazevedo.myshikawa.db.entity.Shikawa;
import br.fazevedo.myshikawa.list.ShikawaListViewModel;
import br.fazevedo.myshikawa.list.ShikawasAdapter;
import br.fazevedo.myshikawa.list.ShikawasViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShikawasListFragment extends Fragment {
    @BindView(R.id.rv_shikawas)
    RecyclerView mRVShikawas;

    private ShikawaListViewModel mShikawasListViewModel;
    private ShikawasAdapter mShikawasAdapter;

    public ShikawasListFragment() {
        //This constructor *has* to be empty for the Fragment Manager to be able to instantiate this
        //fragment (e.g. upon rotation changes).
    }

    public static ShikawasListFragment newInstance() {
        return new ShikawasListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_shikawas_list, container, false);
        ButterKnife.bind(this, view);

        mShikawasAdapter = new ShikawasAdapter(new ArrayList<Shikawa>(), new ShikawasViewHolder.ShikawaListener() {
            @Override
            public void onShikawaClick(Shikawa shikawa) {
                //TODO
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRVShikawas.setLayoutManager(llm);
        mRVShikawas.setHasFixedSize(true);
        mRVShikawas.setAdapter(mShikawasAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                llm.getOrientation());
        mRVShikawas.addItemDecoration(dividerItemDecoration);

        mShikawasListViewModel = ViewModelProviders.of(this).get(ShikawaListViewModel.class);
        mShikawasListViewModel.getShikawasList().observe(this, new Observer<List<Shikawa>>() {
            @Override
            public void onChanged(@Nullable List<Shikawa> shikawas) {
                mShikawasAdapter.setShikawasList(shikawas);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ShikawasViewHolder shikawasViewHolder = (ShikawasViewHolder) viewHolder;
                final Shikawa shikawa = shikawasViewHolder.getShikawa();
                ShikawaUtils.showRemoveDialog(getContext(), ShikawaUtils.DialogForType.SHIKAWA,
                        shikawa.title, new ShikawaUtils.OnRemoveDialogListener() {
                            @Override
                            public void onPositive() {
                                mShikawasListViewModel.deleteShikawa(shikawa);
                            }

                            @Override
                            public void onNegative() {
                                // to re-draw the text
                                mShikawasAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        itemTouchHelper.attachToRecyclerView(mRVShikawas);

        return view;
    }

    public void addShikawa(Shikawa shikawa) {
        mShikawasListViewModel.addShikawa(shikawa);
    }
}
