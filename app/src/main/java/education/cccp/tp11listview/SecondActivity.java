package education.cccp.tp11listview;

import static android.R.layout.simple_list_item_1;
import static education.cccp.tp11listview.R.id.personListViewId;
import static education.cccp.tp11listview.R.layout.activity_second;
import static education.cccp.tp11listview.dao.PersonDao.findAll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import education.cccp.tp11listview.models.Person;

public class SecondActivity extends AppCompatActivity {

    public static final String CURRENT_PERSON_KEY = "current_person_key";
    public static final String CURRENT_PERSON_INDEX_KEY = "current_person_index_key";
    public static final String PERSON_LIST_KEY = "person_list_key";
    public static final int OUT_OF_BOUND_INDEX = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        ListView personsListView = findViewById(personListViewId);
        //noinspection unchecked
        personsListView.setAdapter(new ArrayAdapter<>(this,
                simple_list_item_1,
                (List<Person>) getIntent()
                        .getSerializableExtra(PERSON_LIST_KEY)));

        personsListView.setOnItemClickListener((AdapterView<?> adapterView,
                                                View view,
                                                int index,
                                                long l) -> {
            //retrieve person's clicked
            setResult(RESULT_OK, new Intent()
                    .putExtra(CURRENT_PERSON_KEY, findAll().get(index))
                    .putExtra(CURRENT_PERSON_INDEX_KEY, index));
            finish();
        });
    }

    public void onClickBackButtonEvent(View view) {
        finish();
    }
}