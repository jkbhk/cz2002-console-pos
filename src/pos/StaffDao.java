package pos;

import java.util.ArrayList;

public class StaffDao extends GenericDao<Staff>{

	 private static final String filename = "staff_data.csv";

    @Override
    public ArrayList<Staff> read() {
        ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Staff> staffList = new ArrayList<>();

        if(result != null){
            for (String[] props : result) {
                Staff s = new Staff(props[0],props[1],props[2],props[3]);
                staffList.add(s);
            }        
        }

        return staffList;
    }

    @Override
    public void write(ArrayList<Staff> list) {
        String[][] unwrapped = new String[list.size()][4];
        
        for(int i = 0; i < list.size(); i++){
            
            Staff temp = list.get(i);
            unwrapped[i][0] = temp.getID();
            unwrapped[i][1] = temp.getStaffName();
            unwrapped[i][2] = temp.getGender();
            unwrapped[i][3] = temp.getJobTitle();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
    }


}
