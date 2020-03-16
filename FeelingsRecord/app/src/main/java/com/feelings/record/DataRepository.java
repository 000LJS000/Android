package com.feelings.record;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();

    private final DataDao dataDao;
    private final LiveData<List<Data>> allDatas;

    DataRepository(Application application) {
        DataRoomDatabase db = DataRoomDatabase.getDatabase(application);
        dataDao = db.dataDao();
        allDatas = dataDao.getAllDatas();
    }

    public LiveData<List<Data>> getAllDatas() {
        return allDatas;
    }

    public void insert(Data data) {
        new AsyncTask<Data, Void, Long>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Long doInBackground(Data... data) {
                if (dataDao == null)
                    return -1L;
                /*String name = users[0].getUserName();
                int age = users[0].getAge();*/
//                return userDao.insertUser(name, age);
                return dataDao.insert(data[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Log.d("TAG", "insert : " + aLong);
            }
        };
    }
}