package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.List;
public class BloodGlucoseListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mBloodGlucoseRecyclerView;
    private BloodGlucoseAdapter mAdapter;
    private boolean mSubtitleVisible;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_glucose_list, container, false);
        mBloodGlucoseRecyclerView = (RecyclerView)
                view.findViewById(R.id.blood_glucose_recycler_view);
        mBloodGlucoseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();


        return view;

    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_bloodglucose_list, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_bloodglucose:
                //BloodGlucose bloodglucose = new BloodGlucose();
                Intent intent = new Intent(getActivity(),BloodGlucoseActivity.class);
                //finish();
                startActivity(intent);
                /*BloodGlucoseLab.get(getActivity()).addBloodGlucose(bloodglucose);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getId());
                startActivity(intent);*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }*/

    private void updateUI() {
        BloodGlucoseLab bloodglucoselab = BloodGlucoseLab.get(getActivity());
        List<BloodGlucose> bloodglucose = bloodglucoselab.getBloodGlucose();
        mAdapter = new BloodGlucoseAdapter(bloodglucose);
        mBloodGlucoseRecyclerView.setAdapter(mAdapter);
    }

    private class BloodGlucoseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            mDateTextView.setText(mBloodGlucose.getDate1().toString());
            mIsNormalCheckBox.setChecked(mBloodGlucose.isAbnormal());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),BloodGlucoseActivity.class);
            //finish();
            mAdapter.notifyDataSetChanged();
            startActivity(intent);
        }
    }

        /*@Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }*/

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
