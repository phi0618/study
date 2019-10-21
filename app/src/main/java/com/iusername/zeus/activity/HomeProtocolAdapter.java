package com.iusername.zeus.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iusername.base.Click2Protocol;
import com.iusername.base.utils.UnitUtils;
import com.iusername.zeus.R;

import java.util.List;
import java.util.Map;

public class HomeProtocolAdapter extends RecyclerView.Adapter<HomeProtocolAdapter.ViewHolder> {

    HomeProtocolAdapter(List<Map.Entry<String, String>> list) {
        this.list = list;
    }

    private List<Map.Entry<String, String>> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = new TextView(viewGroup.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(UnitUtils.dip2px(120), UnitUtils.dip2px(80)));
        textView.setBackgroundColor(viewGroup.getContext().getResources().getColor(R.color.colorPrimary));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(0xffffffff);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Map.Entry<String, String> entry = getItemEntity(position);
        viewHolder.mTitleView.setText(entry.getKey());
        viewHolder.mTitleView.setOnClickListener(new Click2Protocol("zeus://readme?za=aa&ca"));
    }

    private Map.Entry<String, String> getItemEntity(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView;
        }
    }
}
