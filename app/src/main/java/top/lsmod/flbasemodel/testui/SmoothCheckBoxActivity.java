package top.lsmod.flbasemodel.testui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.basemodel.constom.SmoothCheckBox;
import top.lsmod.flbasemodel.R;
import top.lsmod.flbasemodel.testui.adapter.SmoothCheckBoxAdapter;
import top.lsmod.flbasemodel.testui.bean.scbBean;

public class SmoothCheckBoxActivity extends FlBaseAppActivity {
    @BindView(R.id.lv_scb)
    ListView lvScb;
    @BindView(R.id.scb)
    SmoothCheckBox scb;
    private SmoothCheckBoxAdapter adapter;
    private List<scbBean> datas;

    @Override
    protected Object initLayout() {
        return R.layout.activity_smooth_check_box;
    }

    @Override
    protected void initView() {
        lvScb.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lvScb.setOnItemClickListener((parent, view, position, id) -> {
            scbBean bean = (scbBean) parent.getAdapter().getItem(position);
            bean.setChecked(!bean.isChecked());
            scb.setChecked(bean.isChecked(), true);
        });
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            scbBean scbBean = new scbBean();
            datas.add(scbBean);
        }
        adapter = new SmoothCheckBoxAdapter(datas, this);
    }
}
