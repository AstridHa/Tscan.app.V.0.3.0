package com.tscan.app.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tscan.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.etiltd.thermalib.Device;

public class Adapter_Sensor extends BaseAdapter {
    private Set<ViewHolder_FragmentSensor> mViewHolders = new HashSet<>();
    private final List<Device> mDeviceList;
    private final Context mContext;

    private JSONObject contactsObj = new JSONObject();
    private JSONArray contactsArray = new JSONArray();

/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_Sensor(Context context, List<Device> deviceList) {
        mDeviceList = deviceList;
        mContext = context;
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        View view = recycledView;
        Device device = mDeviceList.get(position);
        ViewHolder_FragmentSensor viewHolder;

        if (view == null) {
            view = View.inflate(mContext, R.layout.row_sensor, null);
            viewHolder = new ViewHolder_FragmentSensor(view);
            mViewHolders.add(viewHolder);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder_FragmentSensor) view.getTag();

            try {
            for(int i =0; i<mDeviceList.size(); i++) {
                JSONObject contact = new JSONObject();
                contact.put("sensor_id", mDeviceList.get(i).getIdentifier());
                contact.put("sensor_connection", mDeviceList.get(i).getConnectionState());
                contactsArray.put(i, contact);
            }

            contactsObj.put("Sensor", contactsArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        viewHolder.setDevice(device);
        viewHolder.setSensorClass(viewHolder);

        return view;
    }

    @Override
    public int getCount() {
        return mDeviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDeviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private ViewHolder_FragmentSensor findViewHolderForDevice(Device device) {
        ViewHolder_FragmentSensor ans = null;
        for (ViewHolder_FragmentSensor holder : mViewHolders) {
            if (device == holder.getDevice()) {
                ans = holder;
                break;
            }
        }
        return ans;
    }

    public void setFieldsForDevice(Device device) {
        ViewHolder_FragmentSensor viewHolder = findViewHolderForDevice(device);
        if (viewHolder != null) {
            viewHolder.setSensorClass(viewHolder);
        }
    }

    public void clearList() {
        mDeviceList.clear();
    }
}
