package br.fazevedo.myshikawa.list;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.fazevedo.myshikawa.R;
import br.fazevedo.myshikawa.db.entity.RootReason;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RootReasonViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_reason_content)
    LinearLayout mLLContent;
    @BindView(R.id.view_item_status)
    View mViewStatus;
    @BindView(R.id.tv_reason_descr)
    TextView mTVReasonDescr;

    private Context mContext;
    private RootReasonListener mListener;
    private RootReason mRootReason;

    public RootReasonViewHolder(View itemView, RootReasonListener listener) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        mListener = listener;
        mRootReason = null;
    }

    public void onBind(final RootReason rootReason) {
        int color = mContext.getResources().getColor(android.R.color.holo_red_dark);
        if (rootReason.status == 1) {
            color = mContext.getResources().getColor(android.R.color.holo_orange_dark);
        } else if (rootReason.status == 2) {
            color = mContext.getResources().getColor(android.R.color.holo_green_dark);
        }
        mViewStatus.setBackgroundColor(color);
        mTVReasonDescr.setText(rootReason.description);
        mLLContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(rootReason);
            }
        });
        mRootReason = rootReason;
    }

    public RootReason getRootReason() {
        return mRootReason;
    }

    public interface RootReasonListener {
        void onClick(RootReason rootReason);
    }
}
