package com.example.nestedrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.example.nestedrecycler.Adapter.MyItemAdapter;
import com.example.nestedrecycler.Adapter.MyItemGroupAdaper;
import com.example.nestedrecycler.Interface.FirebaseLoadListener;
import com.example.nestedrecycler.model.ItemData;
import com.example.nestedrecycler.model.ItemGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirebaseLoadListener {

    ProgressDialog dialog;
    FirebaseLoadListener firebaseLoadListener;

    RecyclerView recyclerView;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("MyData");
        databaseReference.keepSynced(true);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Hey Wait Please...");
        dialog.setMessage("I am getting your JSON");

        firebaseLoadListener = this;

        recyclerView = findViewById(R.id.events_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFirebaseData();

    }

    private void getFirebaseData(){
        dialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemGroup> itemGroups = new ArrayList<>();
                for(DataSnapshot groupSnapShot:dataSnapshot.getChildren())
                {
                    ItemGroup itemGroup = new ItemGroup();
                    itemGroup.setHeaderTitle(groupSnapShot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<ItemData>>(){};
                    itemGroup.setListItem(groupSnapShot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);

                }
                firebaseLoadListener.onFirebaseLoadSuccess(itemGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                firebaseLoadListener.onFirebaseLoadFailed(databaseError.getMessage());

            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList) {
        MyItemGroupAdaper adapter = new MyItemGroupAdaper(this,itemGroupList);
        recyclerView.setAdapter(adapter);

        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
