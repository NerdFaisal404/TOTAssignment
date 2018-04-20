package com.faisal.totassignment.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faisal.totassignment.R;
import com.faisal.totassignment.adapter.StudentAdapter;
import com.faisal.totassignment.data.Student;
import com.faisal.totassignment.helper.DatabaseHelper;
import com.faisal.utils.MyDividerItemDecoration;
import com.faisal.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentInfoFragment extends Fragment {

    private StudentAdapter mAdapter;
    private List<Student> studentList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noStudentView;

    private DatabaseHelper db;

    public AddStudentInfoFragment() {
        // Required empty public constructor
    }

    public static AddStudentInfoFragment newInstance() {
        AddStudentInfoFragment fragment = new AddStudentInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_student_info, container, false);
        // Inflate the layout for this fragment


        coordinatorLayout = view.findViewById(R.id.coordinator_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        noStudentView = view.findViewById(R.id.empty_student_view);

        db = new DatabaseHelper(getActivity());

        studentList.addAll(db.getAllStudents());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStudentDialog(false, null, -1);
            }
        });

        mAdapter = new StudentAdapter(getActivity(), studentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyStudents();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));

        return view;

    }

    /**
     * Inserting new student in db
     * and refreshing the list
     */
    private void createStudent(String name, String institution, String mobileNo, String emailAddress) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertStudent(name, institution, mobileNo, emailAddress);

        // get the newly inserted student from db
        Student n = db.getStudent(id);

        if (n != null) {
            // adding new student to array list at 0 position
            studentList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyStudents();
        }
    }

    /**
     * Updating student in db and updating
     * item in the list by its position
     */
    private void updateStudents(String name, int position) {
        Student student = studentList.get(position);

        student.setName(name);

        // updating student in db
        db.updateStudents(student);

        // refreshing the list
        studentList.set(position, student);
        mAdapter.notifyItemChanged(position);

        toggleEmptyStudents();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the student from db
        db.deleteStudent(studentList.get(position));

        // removing the student from the list
        studentList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyStudents();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showStudentDialog(true, studentList.get(position), position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }


    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    private void showStudentDialog(final boolean shouldUpdate, final Student student, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity().getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_student_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(view);

        final EditText etName = view.findViewById(R.id.edt_student_name);
        final EditText etInstitution = view.findViewById(R.id.edt_student_instution);
        final EditText etMobileNo = view.findViewById(R.id.edt_student_moblie_no);
        final EditText etEmail = view.findViewById(R.id.edt_student_email_address);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_student_title) : getString(R.string.lbl_edit_student_title));

        if (shouldUpdate && student != null) {
            etName.setText(student.getName());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating student
                if (shouldUpdate && student != null) {
                    // update student by it's id
                    updateStudents(etName.getText().toString(), position);
                } else {
                    // create new student
                    createStudent(etName.getText().toString(), etInstitution.getText().toString(),
                            etMobileNo.getText().toString(), etEmail.getText().toString());
                }
            }
        });
    }

    /**
     * Toggling list and empty students view
     */
    private void toggleEmptyStudents() {
        // you can check studentsList.size() > 0

        if (db.getStudentsCount() > 0) {
            noStudentView.setVisibility(View.GONE);
        } else {
            noStudentView.setVisibility(View.VISIBLE);
        }
    }
}


