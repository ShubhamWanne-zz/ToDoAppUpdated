/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shubham
 */

public class DataManager {
    List<ToDo> list;
    private final String fileName = "ToDoData.dat";
    File file = new File(fileName);
    public List<ToDo> getData(){
            list = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            String line;
            while((line=buffer.readLine())!=null){
                ToDo data= new ToDo();
                String line_arr[]=line.split("\\|");
                data.setTodo(line_arr[0]);
                data.setTodoDate(line_arr[1]);
                list.add(data);
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
    public void AddData(String todo,Date date){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName, true));
            buffer.write(todo+"|"+cal.get(cal.DAY_OF_MONTH)+""+cal.get(cal.MONTH)+""+cal.get(cal.YEAR));
            buffer.newLine();
            buffer.close();
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removedata(String todo){
        Iterator i= list.iterator();
        while(i.hasNext()){
            ToDo temp = (ToDo) i.next();
            if(temp.getTodo().equals(todo)){
                list.remove(temp);
                break;
            }
        }
    }
    public void updateData(){
        boolean file_delete_status = file.delete();
        System.out.println("file_delete_status : "+file_delete_status);
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName));   
            for(ToDo todo : list){
                buffer.write(todo.getTodo()+"|"+todo.getTodoDate());
                buffer.newLine();   
            }
            buffer.close();
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void main(String[] args) {
        DataManager du=new DataManager();
//        du.AddData("Shubham", Calendar.getInstance().getTime());
//        du.AddData("Arun",Calendar.getInstance().getTime());
//        du.AddData("Wanne",Calendar.getInstance().getTime());
        for(ToDo td : du.getData()){
            System.out.println(td.getTodo());
        }
    }
}