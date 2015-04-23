package com.example.propertymgmtapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler {
	
	private static final String DATABASE_NAME = "Appdb";
	private static final int DATABASE_VERSION = 5;
	
	//table names
	private static final String TABLE_PROP = "propTable";
	private static final String TABLE_TENANTS = "tenantsTable";
	private static final String TABLE_CONTRACTORS = "contractorsTable";
	
	//property table
	public static final String PROP_ID = "_id";
	public static final String PROP_NAME = "prop_name";
	public static final String PROP_ADDR = "prop_addr";
	public static final String PROP_OCCUPANTS = "prop_occupants";
	public static final String PROP_RENT = "prop_rent";
	public static final String PROP_NOTES = "prop_notes";
	
	//property table
	public static final String TENANT_ID = "_id";
	public static final String TENANT_NAME = "tenant_name";
	public static final String TENANT_MOBILE = "tenant_mobile";
	public static final String TENANT_EMAIL = "tenant_email";
	public static final String TENANT_ADDR = "tenant_addr";
	public static final String TENANT_RENT = "tenant_rent";
	public static final String TENANT_DEPOSIT = "tenant_deposit";
	public static final String TENANT_NOTES = "tenant_notes";
	
	//contractors table
	public static final String CONTRACTOR_ID = "_id";
	public static final String CONTRACTOR_NAME = "contractor_name";
	public static final String CONTRACTOR_MOBILE = "contractor_mobile";
	public static final String CONTRACTOR_EMAIL = "contractor_email";
	public static final String CONTRACTOR_ADDR = "contractor_addr";
	public static final String CONTRACTOR_TYPE = "contractor_type";
	public static final String CONTRACTOR_CHARGE = "contractor_charge";
	public static final String CONTRACTOR_NOTES = "contractor_notes";
		
	
	//Create table statements
	//Property Table
	private static final String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE_PROP + " (" + PROP_ID + " INTEGER PRIMARY KEY" +
			" AUTOINCREMENT, " + PROP_NAME + " TEXT NOT NULL, " + PROP_ADDR + " TEXT NOT NULL, "
			+PROP_OCCUPANTS+" TEXT NOT NULL, "+PROP_RENT+" TEXT NOT NULL,"+PROP_NOTES+" TEXT NOT NULL);";
	
	//Tenant Table
	private static final String CREATE_TENANT_TABLE = "CREATE TABLE " + TABLE_TENANTS + " (" + TENANT_ID + " INTEGER PRIMARY KEY" +
			" AUTOINCREMENT, " + TENANT_NAME + " TEXT NOT NULL, "+TENANT_MOBILE+" TEXT NOT NULL,"+TENANT_EMAIL+" TEXT NOT NULL," 
			+ TENANT_ADDR + " TEXT NOT NULL, "+TENANT_RENT+" TEXT NOT NULL, "+TENANT_DEPOSIT+" TEXT NOT NULL,"
			+TENANT_NOTES+" TEXT NOT NULL);";
	
	//Contractors Table
		private static final String CREATE_CONTRACTOR_TABLE = "CREATE TABLE " + TABLE_CONTRACTORS + " (" + CONTRACTOR_ID + " INTEGER PRIMARY KEY" +
				" AUTOINCREMENT, " + CONTRACTOR_NAME + " TEXT NOT NULL, "+CONTRACTOR_MOBILE+" TEXT NOT NULL, "+CONTRACTOR_EMAIL+" TEXT NOT NULL," 
				+ CONTRACTOR_ADDR + " TEXT NOT NULL, "+CONTRACTOR_TYPE+" TEXT NOT NULL, "+CONTRACTOR_CHARGE+" TEXT NOT NULL,"
				+CONTRACTOR_NOTES+" TEXT NOT NULL);";
	
	

	
	private  DbHelper helper;
	private final Context context;
	private SQLiteDatabase sqlitedatabase;
	
	
	private static class DbHelper extends SQLiteOpenHelper
	{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
				
			db.execSQL(CREATE_PROP_TABLE);
			db.execSQL(CREATE_TENANT_TABLE);
			db.execSQL(CREATE_CONTRACTOR_TABLE);
		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			//drop older tables
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROP);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TENANTS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTRACTORS);
			
			//create new tables
			onCreate(db);
		}	
		
	}
	
	public DbHandler(Context c)
	{
		context = c;
	}
	
	//opens the db to write to it
	public DbHandler open() throws SQLException
	{
		helper = new DbHelper(context);
		sqlitedatabase = helper.getWritableDatabase();
		return this;
	}
	
	//closes the DB after writing to it
	public void close()
	{
		helper.close();
	}

		//where we actually write into the db
		//property table methods
		public long createEntry(String pName, String pAddr, String pOccupants, String pRent, String pNotes) {
	// TODO Auto-generated method stub
		
		ContentValues cv = new ContentValues();
		//(column to pass it, what to pass in)
		cv.put(PROP_NAME, pName);  	
		cv.put(PROP_ADDR, pAddr);
		cv.put(PROP_OCCUPANTS, pOccupants);
		cv.put(PROP_RENT, pRent);
		cv.put(PROP_NOTES, pNotes);
		
		//table, null, what to put in(puts)
		return sqlitedatabase.insert(TABLE_PROP, null , cv); 	
	}
	
		public String getData() {
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
		
		//Read info through cursor
		Cursor c = sqlitedatabase.query(TABLE_PROP, columns, null, null, null, null, PROP_NAME); 
		String result = "";
		
		int iRow = c.getColumnIndex(PROP_ID);
		int iName = c.getColumnIndex(PROP_NAME);
		int iAddr = c.getColumnIndex(PROP_ADDR);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			result = result + c.getString(iRow)+" "+c.getString(iName)+" "+c.getString(iAddr)+"\n";
		}
		
		return result;
	}
	

		//Tenants Table Methods
			public long createTenantsEntry(String tName, String tMobile, String tEmail, String tAddr, String tRent, String tDeposit, String tNotes) {
				// TODO Auto-generated method stub
					
					ContentValues cv = new ContentValues();
					//(column to pass it, what to pass in)
					cv.put(TENANT_NAME, tName);  	
					cv.put(TENANT_ADDR, tAddr);
					cv.put(TENANT_MOBILE, tMobile);
					cv.put(TENANT_EMAIL, tEmail);
					cv.put(TENANT_RENT, tRent);
					cv.put(TENANT_DEPOSIT, tDeposit);
					cv.put(TENANT_NOTES, tNotes);
					
					//table, null, what to put in(puts)
					return sqlitedatabase.insert(TABLE_TENANTS, null , cv); 	
				}
			
			public String getTenantData() {
				// TODO Auto-generated method stub
				
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				
				//Read info through cursor
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, null, null, null, null, null); 
				String result = "";
				
				int iRow = c.getColumnIndex(TENANT_ID);
				int iName = c.getColumnIndex(TENANT_NAME);
				int iMob = c.getColumnIndex(TENANT_MOBILE);
				//int iOcc = c.getColumnIndex(PROP_OCCUPANTS);
				//int iRent = c.getColumnIndex(PROP_RENT);
				//int iNotes = c.getColumnIndex(PROP_NOTES);
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					result = result + c.getString(iRow)+" "+c.getString(iName)+" "+c.getString(iMob)+"\n";
				}
				
				return result;
			}	
			
			
			//here is where we actually write into the db
			public long createContractorEntry(String cName, String cMobile, String cEmail, String cAddr, String cType, String cCharge, String cNotes) {
			// TODO Auto-generated method stub
				
				ContentValues cv = new ContentValues();
				//(column to pass it, what to pass in)
				cv.put(CONTRACTOR_NAME, cName);
				cv.put(CONTRACTOR_MOBILE, cMobile);
				cv.put(CONTRACTOR_EMAIL, cEmail);
				cv.put(CONTRACTOR_ADDR, cAddr);
				cv.put(CONTRACTOR_TYPE, cType);
				cv.put(CONTRACTOR_CHARGE, cCharge);
				cv.put(CONTRACTOR_NOTES, cNotes);
				
				//table, null, what to put in(puts)
				return sqlitedatabase.insert(TABLE_CONTRACTORS, null , cv);  	
			}
			
			public String getContractorData() {
				// TODO Auto-generated method stub
				
				String[] columns = new String[]{CONTRACTOR_ID, CONTRACTOR_NAME, CONTRACTOR_MOBILE, CONTRACTOR_EMAIL, CONTRACTOR_ADDR, CONTRACTOR_TYPE, CONTRACTOR_CHARGE, CONTRACTOR_NOTES};
				
				//Read info through cursor
				Cursor c = sqlitedatabase.query(TABLE_CONTRACTORS, columns, null, null, null, null, null); 
				String result = "";
				
				int iRow = c.getColumnIndex(CONTRACTOR_ID);
				int iName = c.getColumnIndex(CONTRACTOR_NAME);
				int iMob = c.getColumnIndex(CONTRACTOR_MOBILE);
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					result = result + c.getString(iRow)+" "+c.getString(iName)+" "+c.getString(iMob)+"\n";
				}
				
				return result;
			}

			
			
			//----------------------------------------------------------------
			//---------------Returning Find Property Details ------------------
			//-----------------------------------------------------------------
			//-----------------------------------------------------------------
			public String getName(long l) throws SQLException {
				// TODO Auto-generated method stub
				String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_PROP, columns, PROP_ID + "=" +l, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String name = c.getString(1);
						return name;
				}
				return null;
			}
			
			
			public String getAddress(long l)throws SQLException {
				// TODO Auto-generated method stub
				String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_PROP, columns, PROP_ID + "=" +l, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String address = c.getString(2);
						return address;
				}
				return null;
			}

			public String getOccupants(long l) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_PROP, columns, PROP_ID + "=" +l, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String occupants = c.getString(3);
						return occupants;
				}
				return null;
			}

			public String getRent(long l) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_PROP, columns, PROP_ID + "=" +l, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String rent = c.getString(4);
						return rent;
				}
				return null;
			}

			public String getNotes(long l) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{PROP_ID, PROP_NAME, PROP_ADDR, PROP_OCCUPANTS, PROP_RENT, PROP_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_PROP, columns, PROP_ID + "=" +l, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String notes = c.getString(5);
						return notes;
				}
				return null;
			}

			//modify property details
			public void modifyProp(long lx, String pmName, String pmAddr, String pmOccupants, String pmRent, String pmNotes) 
					throws SQLException{
				// TODO Auto-generated method stub
				
				ContentValues cvModify = new ContentValues();
				cvModify.put(PROP_NAME, pmName);
				cvModify.put(PROP_ADDR, pmAddr);
				cvModify.put(PROP_OCCUPANTS, pmOccupants);
				cvModify.put(PROP_RENT, pmRent);
				cvModify.put(PROP_NOTES, pmNotes);
				
				sqlitedatabase.update(TABLE_PROP, cvModify, PROP_ID+"="+lx, null);
			}

			//delete property
			public void deleteProp(long ld) throws SQLException{
				// TODO Auto-generated method stub
				
				sqlitedatabase.delete(TABLE_PROP, PROP_ID+"="+ld, null);
			}
			
			
			
			//-----------------------------------------------------------------
			//---------------Returning Find Tenant Details-------------------------
			//----------------------------------------------------------------
			//----------------------------------------------------------------
			public String getTenName(long longTen) throws SQLException {
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String name = c.getString(1);
						return name;
				}
				return null;
			}

			public String getTenMob(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String mobile = c.getString(2);
						return mobile;
				}
				return null;
			}

			public String getTenEmail(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String email = c.getString(3);
						return email;
				}
				return null;
			}

			public String getTenAddress(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String address = c.getString(4);
						return address;
				}
				return null;
			}

			public String getTenRent(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String rent = c.getString(5);
						return rent;
				}
				return null;
			}

			public String getTenDeposit(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String deposit = c.getString(6);
						return deposit;
				}
				return null;
			}

			public String getTenNotes(long longTen) throws SQLException{
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTen, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String notes = c.getString(7);
						return notes;
				}
				return null;
			}

			public void modifyTen(long longTenM, String tmName,
					String tmMobile, String tmEmail, String tmAddr,
					String tmRent, String tmDeposit, String tmNotes) {
				// TODO Auto-generated method stub
				
				ContentValues cvTenModify = new ContentValues();
				cvTenModify.put(TENANT_NAME, tmName);
				cvTenModify.put(TENANT_MOBILE, tmMobile);
				cvTenModify.put(TENANT_EMAIL, tmEmail);	
				cvTenModify.put(TENANT_ADDR, tmAddr);
				cvTenModify.put(TENANT_RENT, tmRent);
				cvTenModify.put(TENANT_DEPOSIT, tmDeposit);
				cvTenModify.put(TENANT_NOTES, tmNotes);
				
				sqlitedatabase.update(TABLE_TENANTS, cvTenModify, PROP_ID+"="+longTenM, null);
				
				
			}

			public void deleteTen(long longTenD) {
				// TODO Auto-generated method stub
				
				sqlitedatabase.delete(TABLE_TENANTS, TENANT_ID+"="+longTenD, null);
				
			}


			public String getTenSmsMob(long longTenSms) {
				// TODO Auto-generated method stub
				String[] columns = new String[]{TENANT_ID, TENANT_NAME, TENANT_MOBILE, TENANT_EMAIL, TENANT_ADDR, TENANT_RENT, TENANT_DEPOSIT, TENANT_NOTES};
				Cursor c = sqlitedatabase.query(TABLE_TENANTS, columns, TENANT_ID + "=" +longTenSms, null, null, null, null);
				if(c != null){
					c.moveToFirst();
					String mobile = c.getString(2);
						return mobile;
				}
				return null;
			}
}
