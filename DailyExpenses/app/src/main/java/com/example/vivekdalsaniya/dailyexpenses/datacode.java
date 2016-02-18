package com.example.vivekdalsaniya.dailyexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Vivek Dalsaniya on 1/5/2016.
 */


public class datacode {
    public static final String KEY_ROWID="_id";
    public static final String KEY_DATE="_date";
    public static final String KEY_NAME="_name";
    public static final String KEY_PRICES="_prices";

    private static final String DATABASE_NAME="Daily_Expenses1";
    private static final String DATABASE_TABLE="peopleTable1";
    private static final int DATABASE_VERSION=1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public datacode(Context c) {
        ourContext=c;
    }

    public datacode open() throws SQLException{
        ourHelper =new DbHelper(ourContext);
        ourDatabase =ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }

    public long createEntry(String date,String name,String price){
        ContentValues cv=new ContentValues();
        cv.put(KEY_DATE,date);
        cv.put(KEY_NAME,name);
        cv.put(KEY_PRICES, price);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public String getData() {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }
    public String getDate(long l){
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String date=c.getString(1);
            return date;
        }
        return null;
    }

    public int countSpecific(String date){
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int result=0;
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            if(date.equals(c.getString(iDate)))
                result=result+Integer.parseInt(c.getString(iPrices));

        }
        return result;
    }


    public int countCustom(Date date1, Date date2){
        String x=date1.toString();
        String y=date2.toString();
        String date_1[]=x.split("/");
        String date_2[]=y.split("/");
            GregorianCalendar gc = new GregorianCalendar();
            GregorianCalendar gc1 = new GregorianCalendar();
            gc.setTime(date1);
            gc1.setTime(date2);
            int count=0;
            do {
                String[] columns=new String[]{
                        KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
                };
                Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
                int iRow=c.getColumnIndex(KEY_ROWID);
                int iDate=c.getColumnIndex(KEY_DATE);
                int iName=c.getColumnIndex(KEY_NAME);
                int iPrices=c.getColumnIndex(KEY_PRICES);
                String temp1=this.setdataformat(gc.getTime());
                String temp2=this.setdataformat(gc1.getTime());
                for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
                {

                    if(temp1.equals(c.getString(iDate)))
                        count=count+Integer.parseInt(c.getString(iPrices));

                }
                if(temp1.equals(temp2))
                    break;
                gc.add(Calendar.DATE,1);
            }while(true);

            return count ;
    }

    public String setdataformat(Date x){
        String[] xx=x.toString().split(" ");
        if(xx[1].equals("Jan")){
            xx[1]="1";
        }else if(xx[1].equals("Feb")){
            xx[1]="2";
        }else if(xx[1].equals("Mar")){
            xx[1]="3";
        }else if(xx[1].equals("Apr")){
            xx[1]="4";
        }else if(xx[1].equals("May")){
            xx[1]="5";
        }else if(xx[1].equals("Jun")){
            xx[1]="6";
        }else if(xx[1].equals("Jul")){
            xx[1]="7";
        }else if(xx[1].equals("Aug")){
            xx[1]="8";
        }else if(xx[1].equals("Sep")){
            xx[1]="9";
        }else if(xx[1].equals("Oct")){
            xx[1]="10";
        }else if(xx[1].equals("Nov")){
            xx[1]="11";
        }else{
            xx[1]="12";
        }
        String xxx=xx[2]+"/"+xx[1]+"/"+xx[5];
        return xxx;
    }

    public String checkDate(String[] date_1,String[] date_2){
        if(Integer.parseInt(date_1[2])>Integer.parseInt(date_2[2]))
            return "Enter Valid Data";
        else if(Integer.parseInt(date_1[2])<Integer.parseInt(date_2[2])){
            return "true";
        }
        else {
            if (Integer.parseInt(date_1[1]) < Integer.parseInt(date_2[1])) {
                return "true";
            } else if (Integer.parseInt(date_1[1]) > Integer.parseInt(date_2[1])) {
                return "Enter Valid Data";
            } else {
                if(Integer.parseInt(date_1[0]) < Integer.parseInt(date_2[0])){
                    return "true";
                }
                else if(Integer.parseInt(date_1[0]) > Integer.parseInt(date_2[0])){
                    return "Enter Valid Data";
                }
                else{
                    //  for Same date;
                }
            }
        }
        return null;
    }



    public String FindbyName(String temp){
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            if(temp.equals(c.getString(iName)))
                result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }

    public String getName(long l) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String name=c.getString(2);
            return name;
        }
        return null;
    }

    public String getPrices(long l){
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String prices=c.getString(3);
            return prices;
        }
        return null;
    }

    public void updateEntry(long lRow, String mName, String mPrices) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME,mName);
        cvUpdate.put(KEY_PRICES,mPrices);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    public void deleteEntry(long lRow1) {
        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
    }

    public void deleteEntryByDate(String sDate) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            if (sDate.equals(c.getString(iDate))) {
                ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + c.getString(iRow), null);
            }
        }
    }

    public void deleteEntryByMonth(String x, String y) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        String temp[]=null;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            temp=c.getString(iDate).split("/");
            if(temp[1].equals(x)&&temp[2].equals(y)){
                ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + c.getString(iRow), null);
            }

        }
    }

    public String FindbyId(String xx) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            if(xx.equals(c.getString(iRow)))
            result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }

    public String FindbyPrice(String xx) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            if(xx.equals(c.getString(iPrices)))
                result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }

    public String getDataByDate(String xxx) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            if(xxx.equals(c.getString(iDate)))
                result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }

    public String getDataByMonth(String xxx, String yyy) {
        String[] columns=new String[]{
                KEY_ROWID,KEY_DATE,KEY_NAME,KEY_PRICES
        };
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iDate=c.getColumnIndex(KEY_DATE);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPrices=c.getColumnIndex(KEY_PRICES);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            String temp[]=c.getString(iDate).split("/");
            if(xxx.equals(temp[1])&&yyy.equals(temp[2]))
                result=result+c.getString(iRow)+" "+c.getString(iDate)+" "+c.getString(iName)+" "+c.getString(iPrices)+"\n";
        }
        return result;
    }




    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATE + " TEXT NOT NULL, " +KEY_NAME + " TEXT NOT NULL, " + KEY_PRICES + " TEXT NOT NULL);");       }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}
















