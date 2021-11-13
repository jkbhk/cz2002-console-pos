package dao;

import java.util.ArrayList;

import pos.GenericFileReader;
import pos.GenericFileWriter;

public abstract class GenericDao<T>{

	protected GenericFileWriter writer = null;
	protected GenericFileReader reader = null;
	
	public abstract ArrayList<T> read(); 
	public abstract void write(ArrayList<T> list);
	
	
	

}
