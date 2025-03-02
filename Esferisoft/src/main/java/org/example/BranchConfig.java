/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author Adbeel
 */
import java.util.List;

public class BranchConfig {
    private String branchName;
    private int totalBoxes;
    private List<String> boxTypes;
    private List<User> users;

    // Getters y Setters
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getTotalBoxes() {
        return totalBoxes;
    }

    public void setTotalBoxes(int totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public List<String> getBoxTypes() {
        return boxTypes;
    }

    public void setBoxTypes(List<String> boxTypes) {
        this.boxTypes = boxTypes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
