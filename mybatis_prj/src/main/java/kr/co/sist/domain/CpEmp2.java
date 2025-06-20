package kr.co.sist.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CpEmp2 {

	private int empno, sal, comm, deptno;
	private String ename, job, hiredateStr;
	private Date hiredate;
	
} //class
