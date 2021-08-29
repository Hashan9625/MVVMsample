package com.example.mvvmsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)   //app eke vertion number eka up karana kota methanin up karanna puluwan
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //synchronized walin ekapara access karanna denne ekkenekuta witharai
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null) // kalin ekak thiyanawada balanawa
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),//
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)//---------
                    .build();

        }
// --------------------------------------------------------  instance eka hadagannawa
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //----------------------------------------------------  hadagaththa instance eka execute karanawa
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;
//---------------------------------------------------------------------  execute kala eka populate karanawa
        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title1","description1",1));
            noteDao.insert(new Note("title2","description2",2));
            noteDao.insert(new Note("title3","description3",3));
            noteDao.insert(new Note("title4","description4",4));
            noteDao.insert(new Note("title5","description5",5));
            noteDao.insert(new Note("title6","description6",6));
            return null;
        }
    }
}
