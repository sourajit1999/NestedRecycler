package com.example.nestedrecycler.Interface;

import com.example.nestedrecycler.model.ItemGroup;

import java.util.List;

public interface FirebaseLoadListener {

    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);
}
