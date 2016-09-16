package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
public class BloodGlucoseListFragment extends Fragment {
    private RecyclerView mBloodGlucoseRecyclerView;
    private BloodGlucoseAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_glucose_list, container, false);
        mBloodGlucoseRecyclerView = (RecyclerView) view.findViewById(R.id.blood_glucose_recycler_view);
        mBloodGlucoseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        BloodGlucoseLab bloodglucoselab = BloodGlucoseLab.get(getActivity());
        List<BloodGlucose> bloodglucose = bloodglucoselab.getBloodGlucose();

        mAdapter = new BloodGlucoseAdapter(bloodglucose);
        mBloodGlucoseRecyclerView.setAdapter(mAdapter);
    }

    private class BloodGlucoseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mAverageTextView;
        private TextView mDateTextView;
        private CheckBox mIsNormalCheckBox;

        private BloodGlucose mBloodGlucose;

        public BloodGlucoseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mAverageTextView = (TextView) itemView.findViewById(R.id.txt_Average);
            mDateTextView = (TextView) itemView.findViewById(R.id.txt_Date);
            mIsNormalCheckBox = (CheckBox) itemView.findViewById(R.id.ckb_Abnormal);
        }

        public void bindBloodGlucose(BloodGlucose bloodglucose) {
            mBloodGlucose = bloodglucose;
            mAverageTextView.setText(Double.toString(mBloodGlucose.getAverage()));
            mDateTextView.setText(mBloodGlucose.getDate().toString());
            mIsNormalCheckBox.setChecked(mBloodGlucose.isAbnormal());

            //else if()
        }
        @Override
        public void onClick(View view) {

        }

        /*@Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }*/
    }

    private class BloodGlucoseAdapter extends RecyclerView.Adapter<BloodGlucoseHolder> {

        private List<BloodGlucose> mBloodGlucose;

        public BloodGlucoseAdapter(List<BloodGlucose> bloodglucose) {
            mBloodGlucose = bloodglucose;
        }

        @Override
        public BloodGlucoseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_blood_glucose, parent, false);
            return new BloodGlucoseHolder(view);
        }

        @Override
        public void onBindViewHolder(BloodGlucoseHolder holder, int position) {
            BloodGlucose bloodglucose = mBloodGlucose.get(position);
            holder.bindBloodGlucose(bloodglucose);
        }

        @Override
        public int getItemCount() {
            return mBloodGlucose.size();
        }
    }


}
