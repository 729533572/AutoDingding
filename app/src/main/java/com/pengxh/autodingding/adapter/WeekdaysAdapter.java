package com.pengxh.autodingding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pengxh.app.multilib.widget.SmoothCheckBox;
import com.pengxh.autodingding.R;
import com.pengxh.autodingding.utils.OnCheckedListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/1/8 14:20
 */
public class WeekdaysAdapter extends BaseAdapter {
    private List<String> weekdays;
    private LayoutInflater layoutInflater;
    private OnCheckedListener checkedListener;
    private HashSet<String> hashSet = new HashSet<>();//利用HashSet直接去重

    public void setOnCheckedListener(OnCheckedListener listener) {
        this.checkedListener = listener;
    }

    public WeekdaysAdapter(Context context, List<String> strings) {
        this.weekdays = strings;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return weekdays == null ? 0 : weekdays.size();
    }

    @Override
    public Object getItem(int position) {
        return weekdays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeekDaysViewHolder itemHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_week, null);//共用sos列表项
            itemHolder = new WeekDaysViewHolder();
            itemHolder.weekItemView = convertView.findViewById(R.id.weekItemView);
            itemHolder.weekCheckBox = convertView.findViewById(R.id.weekCheckBox);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (WeekDaysViewHolder) convertView.getTag();
        }
        itemHolder.bindHolder(weekdays.get(position));
        return convertView;
    }

    class WeekDaysViewHolder {
        private TextView weekItemView;
        private SmoothCheckBox weekCheckBox;

        void bindHolder(String s) {
            weekItemView.setText(s);
            weekCheckBox.setOnClickListener(view -> {
                boolean checked = weekCheckBox.isChecked();
                if (checked) {
                    weekCheckBox.setChecked(false);
                    hashSet.remove(s);
                } else {
                    weekCheckBox.setChecked(true);
                    hashSet.add(s);
                }
                checkedListener.getDataList(new ArrayList<>(hashSet));
            });
        }
    }
}
