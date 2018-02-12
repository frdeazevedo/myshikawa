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

    private Context mContext;
    private ShikawaListener mListener;

    public ShikawasViewHolder(View itemView, Context context, ShikawaListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
        mListener = listener;
    }

    public void onBind(final Shikawa shikawa) {
        mTVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onShikawaClick(shikawa);
                }
            }
        });
        mTVTitle.setText(shikawa.description);
    }

    public interface ShikawaListener {
        void onShikawaClick(Shikawa shikawa);
    }
}
