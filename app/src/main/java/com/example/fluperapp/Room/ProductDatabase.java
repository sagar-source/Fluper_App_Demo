package com.example.fluperapp.Room;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fluperapp.BitmapConverter;
import com.example.fluperapp.R;

@Database(entities = {Product.class}, version = 1,  exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static ProductDatabase INSTANCE;

    private static Context context;

    public static ProductDatabase getDatabase(final Context context) {
        INSTANCE.context = context;
        if (INSTANCE == null) {
            synchronized (ProductDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "product_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDao mDao;

        PopulateDbAsync(ProductDatabase db) {
            mDao = db.productDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
                mDao.insert(new Product("Iphone 11 pro","It is a product of Apple.",100000,100500,BitmapConverter.getBytesFromDrawable(INSTANCE.context.getResources(),R.drawable.iphone)));
                mDao.insert(new Product("Redmi k20 pro","It is a product of Xiaomi.",28000,30000, BitmapConverter.getBytesFromDrawable(INSTANCE.context.getResources(),R.drawable.xiaomi)));
                mDao.insert(new Product("Galaxy note 10","It is a product of Samsung.",50000,55000, BitmapConverter.getBytesFromDrawable(INSTANCE.context.getResources(),R.drawable.samsung)));
            return null;
        }
    }
}
