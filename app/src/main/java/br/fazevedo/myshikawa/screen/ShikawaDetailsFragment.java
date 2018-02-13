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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.ShikawaUtils;
import br.fazevedo.myshikawa.db.ShikawaDatabase;
import br.fazevedo.myshikawa.db.entity.RootReason;
import br.fazevedo.myshikawa.db.entity.Shikawa;
import br.fazevedo.myshikawa.list.RootReasonAdapter;
import br.fazevedo.myshikawa.list.RootReasonListViewModel;
import br.fazevedo.myshikawa.list.RootReasonViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShikawaDetailsFragment extends Fragment {
    private static final String ARG_MODEL = "arg-model";

    @BindView(R.id.view_status)
    View mViewStatus;
    @BindView(R.id.tv_descr)
    TextView mTVDescription;
    @BindView(R.id.tv_reasons_title) //TODO: visibility gone when no reasons.
    TextView mTVReasonsTitle;
    @BindView(R.id.rv_reasons)
    RecyclerView mRVReasons;

    private RootReasonListViewModel mRootReasonsListViewModel;
    private RootReasonAdapter mRootReasonAdapter;

    enum Type {
        SHAKAWA,
        REASON
    }

    public ShikawaDetailsFragment() {
    }

    public static ShikawaDetailsFragment newInstance(Shikawa shikawa) {
        ShikawaDetailsFragment frg = new ShikawaDetailsFragment();
        Bundle args = new Bundle();

        args.putLong(ARG_MODEL, shikawa.id);

        return frg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_MODEL)) {
            Shikawa shikawa = ShikawaDatabase.getInstance(getContext()).getShikawaDao().getShikawa(
                    savedInstanceState.getLong(ARG_MODEL));
            mViewStatus.setVisibility(View.GONE);
            mTVDescription.setText(shikawa.description);
        }

        mRootReasonAdapter = new RootReasonAdapter(new ArrayList<RootReason>(), new RootReasonViewHolder.RootReasonListener() {
            @Override
            public void onClick(RootReason rootReason) {
                //TODO
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRVReasons.setLayoutManager(llm);
        mRVReasons.setHasFixedSize(true);
        mRVReasons.setAdapter(mRootReasonAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                llm.getOrientation());
        mRVReasons.addItemDecoration(dividerItemDecoration);

        mRootReasonsListViewModel = ViewModelProviders.of(this)
                .get(RootReasonListViewModel.class);
        mRootReasonsListViewModel.getRootReasons().observe(this, new Observer<List<RootReason>>() {
            @Override
            public void onChanged(@Nullable List<RootReason> rootReasons) {
                mRootReasonAdapter.setRootReasonList(rootReasons);
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
                RootReasonViewHolder rootReasonViewHolder = (RootReasonViewHolder) viewHolder;
                final RootReason rootReason = rootReasonViewHolder.getRootReason();
                ShikawaUtils.showRemoveDialog(getContext(), ShikawaUtils.DialogForType.SHIKAWA,
                        getString(R.string.reason), new ShikawaUtils.OnRemoveDialogListener() {
                            @Override
                            public void onPositive() {
                                mRootReasonsListViewModel.deleteRootReason(rootReason);
                            }

                            @Override
                            public void onNegative() {
                                // to re-draw the text
                                mRootReasonAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        itemTouchHelper.attachToRecyclerView(mRVReasons);
        return view;
    }


}
