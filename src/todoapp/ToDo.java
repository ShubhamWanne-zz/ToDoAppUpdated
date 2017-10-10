/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp;

/**
 *
 * @author shubham_wanne
 */
class ToDo{
    String todo;
    String todoDate;

    public String getTodo() {
        return todo;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    @Override
    public String toString() {
        return "ToDo{" + "todo=" + todo + ", todoDate=" + todoDate + '}';
    }
    
    public static void main(String[] args) {
        ToDo data = new ToDo();
        data.setTodo("Todo1");
        data.setTodoDate("12345");
        System.out.println(data.getTodo()+" "+data.getTodoDate());
    }
}
