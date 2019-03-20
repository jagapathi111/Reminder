package com.example.android.notes;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class lunchActivity extends Fragment {

    DatabaseHelper myDb;
    EditText edititem,editdate, editid ;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    DatePickerDialog picker;

    @Override

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_lunch,container,false);

        ((MainActivity)getActivity()).setActionBarTitle("Lunch");

        myDb = new DatabaseHelper(this.getContext().getApplicationContext(),null, null, 2);

        edititem = (EditText)rootView.findViewById(R.id.editText_item);
        editdate = (EditText)rootView.findViewById(R.id.editText_date);
        editid = (EditText)rootView.findViewById(R.id.editText_id);

        btnAddData = (Button)rootView.findViewById(R.id.button_save);
        btnviewAll = (Button)rootView.findViewById(R.id.button_view);
        btnDelete = (Button)rootView.findViewById(R.id.button_delete);
        AddData();
        viewAll();
        DeleteData();


        //not to save empty data

        btnAddData.setEnabled(false); // set button disable initially

        edititem.addTextChangedListener(new TextWatcher() {



            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

                if (s.toString().equals("")) {
                    btnAddData.setEnabled(false);
                } else {
                    btnAddData.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });



        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(lunchActivity.this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        return rootView;

    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData1(edititem.getText().toString(),
                                editdate.getText().toString() );
                         if(isInserted == true)

                           Toast.makeText( lunchActivity.this.getActivity() ,"Saved Successfully!",Toast.LENGTH_LONG).show();

                           else

                            Toast.makeText(lunchActivity.this.getActivity(),"Data not Inserted",Toast.LENGTH_LONG).show();
                        edititem.setText("");
                        editdate.setText("");
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData1();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Empty","No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id:"+res.getString(0)+"\n");
                            buffer.append("Lunch :"+ res.getString(1)+"\n");
                            buffer.append("Date :"+ res.getString(2)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Integer deletedRow =  myDb.deleteData1(editid.getText().toString());
                        if(deletedRow > 0)
                            Toast.makeText(lunchActivity.this.getActivity(),"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(lunchActivity.this.getActivity(),"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // return true;
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }







}
