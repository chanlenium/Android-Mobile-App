package com.example.get_set_data_firebase

class EmployeeModel{
    var employeeName: String = ""
    var employeeSalary: Int = 0

    constructor(employeeName: String, employeeSalary: Int) {
        this.employeeName = employeeName
        this.employeeSalary = employeeSalary
    }
}