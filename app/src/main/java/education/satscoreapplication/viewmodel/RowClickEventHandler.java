package education.satscoreapplication.viewmodel;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ghoshr on 3/31/2018.
 */

public interface RowClickEventHandler {
    void onRowClicked(View view, TextView schoolName) ;
}
