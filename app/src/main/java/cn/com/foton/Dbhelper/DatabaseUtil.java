package cn.com.foton.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import cn.com.foton.CopyDb;

public class DatabaseUtil {

	/* 数据库版本号 */
	private static final int DB_VERSION = 1;
	/* 数据库名o�? */
	private static final String DB_NAME = "test.db";
	/**
	 * Context
	 */
	private final Context context;
	private DatabaseHelper SqlHelper;
	private SQLiteDatabase db;

	/**
	 * SQLiteOpenHelper是一个辅助类，用来管理数据库的创建和版本他，它提供两个方面的功能
	 * 第一，getReadableDatabase()�?
	 * getWritableDatabase()可以获得SQLiteDatabase对象，�?�过该对象可以对数据库进行操�?
	 * 第二，提供了onCreate()、onUpgrade()两个回调函数，允许我们再创建和升级数据库时，进行自己的操�?
	 */
	public class DatabaseHelper extends SQLiteOpenHelper {


		public DatabaseHelper(Context context) {
			// 必须通过super调用父类当中的构造函�?
			
			super(context, DB_NAME, null, DB_VERSION);
			
		}

		// 该函数是在第�?次创建的时�?�执行，实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方�?
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			// execSQL用于执行SQL语句
			db.execSQL("create table user(id int,name varchar(20))");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			System.out.println("upgrade a database");
		}

	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param context
	 *            the Context within which to work
	 */
	public DatabaseUtil(Context context) {
		this.context = context;
	}

	/**
	 * This method is used for creating/opening connection
	 * 
	 * @return instance of DatabaseUtil
	 * @throws SQLException
	 */
	public DatabaseUtil open() throws SQLException {
//		SqlHelper = new DatabaseHelper(context);
//		db = SqlHelper.getWritableDatabase();
		String DATABASE_NAME = "test.db";
		  String oldPath = "data/data/cn.com.foton/databases/" + DATABASE_NAME;
		db=SQLiteDatabase.openOrCreateDatabase(oldPath, null);
		return this;
	}

	/**
	 * This method is used for closing the connection.
	 */
	public void close() {
		SqlHelper.close();
	}

	/**
	 * This method is used to create/insert new record Student record.
	 * 
	 * @param name
	 * @param grade
	 * @return long
	 */
	public long createStudent(String db_table, String[] name, String[] grade) {
		ContentValues initialValues = new ContentValues();
		for (int i = 0; i < name.length; i++) {
			initialValues.put(name[i], grade[i]);
		}
		return db.insert(db_table, null, initialValues);
	}

	/**
	 * This method will delete Student record.
	 * 
	 * @param db_table
	 * @param whereClause
	 * @param whereArgs
	 * @return boolean
	 */
	public boolean deleteStudent(String db_table, String whereClause,
			String[] whereArgs) {
		return db.delete(db_table, whereClause, whereArgs) > 0;
	}


	public boolean updateStudent(String db_table, String[] name,
			String[] grade, String whereClause, String[] whereArgs) {
		ContentValues args = new ContentValues();
		for (int i = 0; i < name.length; i++) {
			args.put(name[i], grade[i]);
		}
		return db.update(db_table, args, whereClause, whereArgs) > 0;
	}

	/**
	 * This method will return Cursor holding all the Student records.
	 * 
	 * @param db_table
	 * @param colums
	 * @param selection
	 * @param selectionArgs
	 * @param orderBy
	 * @return Cursor
	 */
	public Cursor fetchAllStudents(String db_table, String[] colums,
			String selection, String[] selectionArgs, String orderBy) {
		return db.query(db_table, colums, selection, selectionArgs, null, null,
				orderBy, null);
	}

	/**
	 * This method will return Cursor holding the specific Student record.
	 * 
	 * @param db_table
	 * @param colums
	 * @param selection
	 * @param selectionArgs
	 * @param orderBy
	 * @return Cursor
	 * @throws SQLException
	 */
	public Cursor fetchStudent(String db_table, String[] colums,
			String selection, String[] selectionArgs, String orderBy)
			throws SQLException {
		Cursor mCursor = db.query(db_table, colums, selection, selectionArgs,
				null, null, orderBy, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * 判断某张表是否存
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public boolean tabbleIsExist(String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}
		Cursor cursor = null;
		try {
			cursor = db.query(tableName, null, null, null, null, null, null,
					null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * This create sqlite The operating.
	 * 
	 * @param sql
	 * @return void
	 */
	public void createExec(String sql) {
		db.execSQL(sql);
		
	}
	public Cursor rawQuery(String sql,String[] s){
		return  db.rawQuery(sql, s);
	}
	
	
	/**
	 * dbUtil = new DatabaseUtil(this); dbUtil.open();
	 * System.out.println("--add------------"+dbUtil.createStudent("user",new
	 * String[] {"id","name"},new String[] {"121","40think"}));
	 * System.out.println("--delete------------"+dbUtil.deleteStudent("user",
	 * "id=?", new String[] {"2"}));
	 * System.out.println("--update------------"+dbUtil.updateStudent("user",
	 * new String[] {"id","name"}, new String[] {"23","修改测试"},
	 * "id=?  and name=? ", new String[] {"3", "sss"})); Cursor cursors =
	 * dbUtil.fetchStudent("user", new String[] {"id","name"}, "id=?", new
	 * String[] {"23"}, null); dbUtil.close();
	 * 
	 * 
	 */

}
