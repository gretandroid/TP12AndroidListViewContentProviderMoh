package education.cccp.tp11listview;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static education.cccp.tp11listview.R.id.editTextPersonFirstNameId;
import static education.cccp.tp11listview.R.id.editTextPersonLastNameId;
import static education.cccp.tp11listview.R.layout.activity_main;
import static education.cccp.tp11listview.SecondActivity.CURRENT_PERSON_INDEX_KEY;
import static education.cccp.tp11listview.SecondActivity.CURRENT_PERSON_KEY;
import static education.cccp.tp11listview.SecondActivity.OUT_OF_BOUND_INDEX;
import static education.cccp.tp11listview.SecondActivity.PERSON_LIST_KEY;
import static education.cccp.tp11listview.dao.PersonDao.delete;
import static education.cccp.tp11listview.dao.PersonDao.findAll;
import static education.cccp.tp11listview.dao.PersonDao.save;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Objects;

import education.cccp.tp11listview.models.Person;

public class MainActivity extends AppCompatActivity {

    public static final String EMPTY_FIELD = "";
    private EditText personFirstNameEditText;
    private EditText personLastNameEditText;
    private int currentIndex = OUT_OF_BOUND_INDEX;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    private void setEditTextPersonFields(String firstName, String lastName) {
        personFirstNameEditText.setText(firstName);
        personLastNameEditText.setText(lastName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        personFirstNameEditText = findViewById(editTextPersonFirstNameId);
        personLastNameEditText = findViewById(editTextPersonLastNameId);
        intentActivityResultLauncher = registerForActivityResult(
                new StartActivityForResult(),
                (ActivityResult activityResult) -> {
                    Intent data = activityResult.getData();
                    if (activityResult.getResultCode() == RESULT_OK) {
                        Person person = (Person) Objects.requireNonNull(data)
                                .getSerializableExtra(CURRENT_PERSON_KEY);
                        setEditTextPersonFields(person.getFirstName(),
                                person.getLastName());
                        currentIndex = data.getIntExtra(
                                CURRENT_PERSON_INDEX_KEY,
                                OUT_OF_BOUND_INDEX);
                    }
                }
        );
    }

    public void onClickCreateButtonEvent(View view) throws Exception {
        save(new Person(
                personFirstNameEditText.getText().toString(),
                personLastNameEditText.getText().toString()));
        makeText(this,
                "person successfully added",
                LENGTH_LONG)
                .show();
        setEditTextPersonFields(EMPTY_FIELD,
                EMPTY_FIELD);
    }

    public void onClickShowAllButtonEvent(View view) {
        intentActivityResultLauncher.launch(new Intent(
                this,
                SecondActivity.class).putExtra(
                PERSON_LIST_KEY,
                (Serializable) findAll()));
    }

    public void onClickDeleteButtonEvent(View view) {
        if (currentIndex != OUT_OF_BOUND_INDEX) {
            delete(currentIndex);
            setEditTextPersonFields(EMPTY_FIELD, EMPTY_FIELD);
            makeText(this,
                    "person successfully deleted",
                    LENGTH_SHORT).show();
        }
    }

    public void onClickEditButtonEvent(View view) {
        Person person = findAll().get(currentIndex);
        person.setLastName(personLastNameEditText.getText().toString());
        person.setFirstName(personFirstNameEditText.getText().toString());
        save(currentIndex, person);
        makeText(this,
                "person successfully updated",
                LENGTH_SHORT).show();
    }
}