package com.burquemaps;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class BurqueMapsMainActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_burque_maps_main);

        setListAdapter(new SampleAdapter(querySampleActivities()));
    }

    @Override
    protected void onListItemClick(ListView lv, View v, int pos, long id) {
        SampleInfo info = (SampleInfo) getListAdapter().getItem(pos);
        startActivity(info.intent);
    }

    @SuppressLint("NewApi")
	protected List<SampleInfo> querySampleActivities() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setPackage(getPackageName());
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);

        ArrayList<SampleInfo> samples = new ArrayList<SampleInfo>();

        final int count = infos.size();
        for (int i = 0; i < count; i++) {
            final ResolveInfo info = infos.get(i);
            final CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

            Intent target = new Intent();
            target.setClassName(info.activityInfo.applicationInfo.packageName,
                    info.activityInfo.name);
            SampleInfo sample = new SampleInfo(label, target);
            samples.add(sample);
        }

        return samples;
    }

    static class SampleInfo {
        String name;
        Intent intent;

        SampleInfo(String name, Intent intent) {
            this.name = name;
            this.intent = intent;
        }
    }

    class SampleAdapter extends BaseAdapter {
        private List<SampleInfo> mItems;

        public SampleAdapter(List<SampleInfo> items) {
            mItems = items;
        }

        //@Override
        public int getCount() {
            return mItems.size();
        }

        //@Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        //@Override
        public long getItemId(int position) {
            return position;
        }

        //@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1,
                        parent, false);
                convertView.setTag(convertView.findViewById(android.R.id.text1));
            }
            TextView tv = (TextView) convertView.getTag();
            tv.setText(mItems.get(position).name);
            return convertView;
        }

    }
}
