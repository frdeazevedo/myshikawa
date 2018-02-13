package br.fazevedo.myshikawa.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.db.entity.Shikawa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShikawasViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_shikawa_title)
    TextView mTVTitle;

    private Shikawa mShikawa;
    private ShikawaListener mListener;

    ShikawasViewHolder(View itemView, ShikawaListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mListener = listener;
        mShikawa = null;
    }

    void onBind(final Shikawa shikawa) {
        mTVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onShikawaClick(shikawa);
                }
            }
        });
        mTVTitle.setText(shikawa.title);
        mShikawa = shikawa;
    }

    public Shikawa getShikawa() {
        return mShikawa;
    }

    public interface ShikawaListener {
        void onShikawaClick(Shikawa shikawa);
    }
}
