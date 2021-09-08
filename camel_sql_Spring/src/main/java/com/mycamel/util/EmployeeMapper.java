package com.mycamel.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycamel.domain.Employee;

public class EmployeeMapper {
	
	public Map<String, Object> getMap(Employee employee) {
        Map<String, Object> answer = new HashMap<String, Object>();
        answer.put("EmpId", employee.getEmpId());
        answer.put("EmpName", employee.getEmpName());
        return answer;
    }

    public List<Employee> readEmployees(List<Map<String, String>> dataList) {

        System.out.println("data:" + dataList);

        List<Employee> employees = new ArrayList<Employee>();

        for (Map<String, String> data : dataList) {

            Employee employee = new Employee();

            employee.setEmpId(data.get("EmpId"));
            employee.setEmpName(data.get("EmpName"));

            employees.add(employee);
        }

        return employees;
    }

}
