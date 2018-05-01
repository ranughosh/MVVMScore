package education.satscoreapplication.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import education.satscoreapplication.R;
import education.satscoreapplication.appconstants.Constants;

public class SchoolActivity extends AppCompatActivity implements SchoolListFragment.ReplaceFragmentInterface {

    private static final String FRAG_LINK_SCHOOL_LIST = "linkSChoolList";
    private static final String FRAG_LINK_SAT_SCORE = "linkSatScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_activity_layout);
        if(savedInstanceState==null){
            addSchoolListFragment();
        }

    }

    private void addSchoolListFragment() {
        SchoolListFragment schoolListFragment=new SchoolListFragment();
        FragmentTransaction fragTrxn=getSupportFragmentManager().beginTransaction();
        fragTrxn.add(R.id.school_list_container,schoolListFragment,FRAG_LINK_SCHOOL_LIST).commit();
        schoolListFragment.setCallBackListener(this);
    }



    private void addSatScoreFragment(String schoolName){
        SatScoreFragment satScoreFragment=new SatScoreFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constants.SCHOOL_NAME,schoolName);
        satScoreFragment.setArguments(bundle);
        FragmentTransaction fragTrxn=getSupportFragmentManager().beginTransaction();
        fragTrxn.replace(R.id.school_list_container,satScoreFragment,FRAG_LINK_SAT_SCORE).addToBackStack(null).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void replaceWithSatScore(String schoolName) {
        addSatScoreFragment(schoolName);
    }
}
