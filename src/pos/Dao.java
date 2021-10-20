package pos;

import java.util.ArrayList;

public interface Dao<T> {

	public ArrayList<T> read();	
	public void write(ArrayList<T> list);
}
