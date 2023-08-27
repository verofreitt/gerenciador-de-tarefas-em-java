/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save(Task task) {
        String sql = "INSERT INTO tasks (idProject, name, description, "
                + "completed, notes, deadline, creatdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?,"
                + "?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(
                    task.getDeadline().getTime()));
            statement.setDate(7, new Date(
                    task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(
                    task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar"
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Task task) {
    
    }
    
    public void removeById(int taskId) throws SQLException {
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public List<Task> getAll(int idProject) {
        return null;
    }
    
}
