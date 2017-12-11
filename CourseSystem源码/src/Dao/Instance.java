package Dao;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import objectStorage.objectMap;
import util.DBHelper;

/**
 * 
 * @author liuliming
 *
 */
public class  Instance{
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
	//公有方法，输入object，程序自动解析object的属性，生成sql语句在数据库中查询其对应属性值并对其赋值
	public static Object Instantiation(Object obj,objectMap objMap) throws Exception{
		String sql=sqlFormation(obj);
		ResultSet rs=getResultSet(sql);
		setFields(obj,rs,objMap);
		rs.close();
		pstmt.close();
		return obj;
	}
	
	
	private static Field[] getAllFields(Object obj){
		Class cl=obj.getClass();
		Field[] fields=cl.getDeclaredFields();
		return fields;
	}
	
	
	private static int getId(Object obj) throws Exception{
		Class cl=obj.getClass();
		Field[] fields=cl.getDeclaredFields();
		for(Field f:fields){
			if("id".equals(f.getName())){
			AccessibleObject.setAccessible(fields, true);
			Class t=f.getType();
			Object id=f.get(obj);
			int returnId=(Integer)id;
			return returnId;
			}
		}
		return 0;
	}
	
	
	private static String sqlFormation(Object obj)throws Exception{
		Class cl=obj.getClass();
		String clName=cl.getName();
		Field[] f=getAllFields(obj);
		String[] tempArray=clName.split("\\.");
		String tableName=tempArray[tempArray.length-1].toLowerCase();
		int id=getId(obj);
		StringBuffer sb=new StringBuffer();
		
		sb.append(" SELECT ");
		for(Field field:f){
			sb.append(field.getName()+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(" FROM "+tableName+" WHERE id= "+getId(obj)+";");
		String sql=sb.toString();
		return sql;
	}
	
	
	private static ResultSet getResultSet(String sql) throws Exception{
		pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		return rs;	
	}
	
	
	private static void setFields(Object obj,ResultSet rs,objectMap objMap)throws Exception{
		Field[] fields=getAllFields(obj);
		while(rs.next()){
		for(int x=0;x<fields.length;x++){
			boolean isFromModels=fields[x].getType().getName().startsWith("models");
			String fieldTypeName=fields[x].getType().getName().split("\\.")[fields[x].getType().getName().split("\\.").length-1];
			if(isFromModels)
			{
			    /*String typeName=fields[x].getType().getName().split("\\.")[1];
			    Method findType=objectMap.class.getMethod("find"+typeName, int.class);
			    if(findType.invoke(objMap, rs.getInt(x+1))!=null)
			    {
			    	Method setter=fields[x].getType().getMethod("set"+typeName,int.class);
			    	setter.invoke(obj, (Integer)rs.getInt(x+1));
			    }
			    else
			    {
			    */
			    	Class cl=Class.forName(fields[x].getType().getName());
			    	Object newObj=cl.newInstance();
			    	Method setId=cl.getMethod("setId", int.class);
			    	setId.invoke(newObj, (Integer)rs.getInt(x+1));
			    	Instantiation(newObj,objMap);
			    	Method setField=obj.getClass().getMethod("set"+fieldTypeName, cl);
			    	setField.invoke(obj, newObj);
			    //}
			    
			}
			else
			{
				if(fields[x].getType().getName().equals("int"))
				{
			    String methodName="set"+fields[x].getName().substring(0, 1).toUpperCase()+fields[x].getName().substring(1).toLowerCase();
			    Method setter=obj.getClass().getMethod(methodName, int.class);
			    setter.invoke(obj, new Integer(rs.getInt(x+1)));
				}
				else if(fields[x].getType().getName().equals("double"))
				{
				 String methodName="set"+fields[x].getName().substring(0, 1).toUpperCase()+fields[x].getName().substring(1).toLowerCase();
				 Method setter=obj.getClass().getMethod(methodName, double.class);
				 setter.invoke(obj, rs.getDouble(x+1));
				}
				else if(fields[x].getType().getName().equals("java.lang.String"))
				{
				String methodName="set"+fields[x].getName().substring(0, 1).toUpperCase()+fields[x].getName().substring(1).toLowerCase();
				Method setter=obj.getClass().getMethod(methodName, String.class);
				setter.invoke(obj, rs.getString(x+1));
				}
			}
		}}}}