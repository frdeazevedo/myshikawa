package br.fazevedo.myshikawa.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.db.entity.Shikawa;

public class ShikawasAdapter extends RecyclerView.Adapter<ShikawasViewHolder> {
    private ShikawasViewHolder.ShikawaListener mListener;
    private List<Shikawa> mShikawas;

    public ShikawasAdapter(List<Shikawa> shikawas, ShikawasViewHolder.ShikawaListener listener) {
        mListener = listener;
        mShikawas = shikawas;
    }

    public void setShikawasList(List<Shikawa> shikawas) {
        mShikawas = shikawas;
        notifyDataSetChanged();
    }

    @Override
    public ShikawasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shikawa_item, parent, false);
        return new ShikawasViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ShikawasViewHolder holder, int position) {
        holder.onBind(mShikawas.get(position));
    }

    @Override
    public int getItemCount() {
        return mShikawas == null ? 0 : mShikawas.size();
    }
}
