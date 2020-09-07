package top.lsmod.flbasemodel.testui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.basemodel.constom.SmoothCheckBox;
import top.lsmod.flbasemodel.R;
import top.lsmod.flbasemodel.testui.bean.scbBean;

public class SmoothCheckBoxAdapter extends BaseAdapter {

    private List<scbBean> datas;
    private Activity context;

    public SmoothCheckBoxAdapter(List<scbBean> datas, Activity context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isEmpty() {
        return datas.size() == 0;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = context.getLayoutInflater().inflate(R.layout.item_common, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        scbBean bean = datas.get(i);
        viewHolder.scb.setOnCheckedChangeListener((checkBox, isChecked) -> bean.setChecked(isChecked));
        viewHolder.tv.setText(String.valueOf(i));
        viewHolder.scb.setChecked(bean.isChecked());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.scb)
        SmoothCheckBox scb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}