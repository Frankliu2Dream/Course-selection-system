package Dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import util.DBHelper;

public class Storage {
	private static Connection conn=null;
	static{
		try{
			conn=DBHelper.getConnection();
		}
		catch(Exception e)
		{
			System.out.println("数据库连接异常！");
		}
	}
	
	private static PreparedStatement pstmt;
	
	//输入任意Object,export方法会自动将其写入数据库，如果写入成功则返回1，没有写入成功会报错
	public static int ItemStorage(Object obj) throws Exception{
		String sql=sqlFormation(obj);
		pstmt=conn.prepareStatement(sql);
		int x=pstmt.executeUpdate(sql);
		pstmt.close();
		return x;
	}
	
	//私有方法SQLFormation输入Object，读取该Object的域，生成sql的insert语句并返回
	private static String sqlFormation(Object obj) throws Exception{
		Field[] fields=obj.getClass().getDeclaredFields();//取得所有域
		
		ArrayList<String> fieldNames=new ArrayList<String>();//取得所有域名
		for(int x=0;x<fields.length;x++){
			fieldNames.add(fields[x].getName());
		}
		
		String objClassName=obj.getClass().getName();//取得表名（为java类名的小写）
		String[] t=objClassName.split("\\.");
		String tableName=t[t.length-1].toLowerCase();
		
		
		StringBuffer sb=new StringBuffer();//利用StringBuffer生成sql语句
		sb.append(" INSERT INTO "+tableName+"(");
		for(int x=0;x<fields.length;x++){
			if(x!=0)sb.append(",");
			sb.append(fields[x].getName());
		}
		sb.append(") VALUES( ");
		for(int x=0;x<fieldNames.size();x++){
			boolean isNumber=fields[x].getType().getName().contains("int")||
					fields[x].getType().getName().contains("double");
			boolean isString=fields[x].getType().getName().equals("java.lang.String");
			boolean isOtherType=!(isNumber||isString);
			if(isNumber){
				Method getter=obj.getClass().getMethod("get"+fields[x].getName().substring(0, 1).toUpperCase()
						+fields[x].getName().substring(1).toLowerCase(),new Class<?>[0]);
				Object value=getter.invoke(obj,null);
				sb.append(value.toString()+",");
			}
			else if(isString){
				Method getter=obj.getClass().getMethod("get"+fields[x].getName().substring(0, 1).toUpperCase()
						+fields[x].getName().substring(1).toLowerCase(), new Class<?>[0]);
				Object value=getter.invoke(obj, null);
				  sb.append("\"");
				sb.append(value.toString());
				sb.append("\"");
				sb.append(",");
			}
			else if(isOtherType){
				Method getter=obj.getClass().getMethod("get"+fields[x].getName().substring(0, 1).toUpperCase()
						+fields[x].getName().substring(1).toLowerCase(), new Class<?>[0]);
				Object fieldObject=getter.invoke(obj,null);
				
				if(fieldObject==null){
					sb.append("null");
				}
				else{
					Object idNumber=fieldObject.getClass().getMethod("getId",null).invoke(fieldObject, null);
					sb.append(idNumber.toString());
					
				}
				sb.append(",");
			}
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(");");
		String sql=sb.toString();
		return sql;
		
	}
	
	
}
