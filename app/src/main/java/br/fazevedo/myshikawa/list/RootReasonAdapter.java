package br.fazevedo.myshikawa.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.db.entity.RootReason;

public class RootReasonAdapter extends RecyclerView.Adapter<RootReasonViewHolder> {
    RootReasonViewHolder.RootReasonListener mRootReasonListener;
    private List<RootReason> mRootReasons;

    public RootReasonAdapter(List<RootReason> reasons, RootReasonViewHolder.RootReasonListener listener) {
        mRootReasonListener = listener;
        mRootReasons = reasons;
    }

    public void setRootReasonList(List<RootReason> reasons) {
        mRootReasons = reasons;
        notifyDataSetChanged();
    }

    public void deleteRootReason(RootReason reason) {

    }

    @Override
    public RootReasonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reason_item, parent, false);
        return new RootReasonViewHolder(view, mRootReasonListener);
    }

    @Override
    public void onBindViewHolder(RootReasonViewHolder holder, int position) {
        holder.onBind(mRootReasons.get(position));
    }

    @Override
    public int getItemCount() {
        return mRootReasons.size();
    }
}
