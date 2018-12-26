package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserLab {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public UserLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryUsers(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.UserTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateUser(User user){
        String username=user.getUsername();
        ContentValues values=getContentValues(user);

        mDatabase.update(DBSchema.UserTable.Name,values, DBSchema.UserTable.Cols.Username+"= ?",
                new String[]{username});

    }

    public void addUser(User u){
        ContentValues values=getContentValues(u);
        mDatabase.insert(DBSchema.UserTable.Name,null,values);
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.UserTable.Cols.Name, user.getName());
        values.put(DBSchema.UserTable.Cols.Username, user.getUsername());
        values.put(DBSchema.UserTable.Cols.Password, user.getPassword());

        return values;
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        ClinicCursorWrapper cursor = queryUsers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    public boolean Username_exists(String Username) {

        List<User> users = getUsers();

        int j=users.size();
        for (int i=0; i<j; i++)
        {
            if( users.get(i).getUsername().equalsIgnoreCase(Username))
                return true;

        }

        return false;

    }

    public boolean check_username_password(String Uname, String Pass) {
        List<User> users=getUsers();

        int j=users.size();

        for (int i=0; i<j; i++)
        {
            if (users.get(i).getUsername().equals(Uname) && users.get(i).getPassword().equals(Pass))
                return true;

        }

        return false;
    }




}
