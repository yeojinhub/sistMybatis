package day0610;

import java.util.List;

import kr.co.sist.domain.Car;
import kr.co.sist.domain.CpEmp;

public class SelectService4 {

	public List<CpEmp> union() {
		List<CpEmp> empList=null;
		
		SelectDAO4 sDAO4=SelectDAO4.getInstance();
		empList=sDAO4.union();
		
		return empList;
	} //union
	
	public List<Car> join(String model) {
		List<Car> carList=null;
		
		SelectDAO4 sDAO4=SelectDAO4.getInstance();
		carList=sDAO4.join(model);
		
		return carList;
	} //join
	
	public List<String> searchAllModel() {
		List<String> modelList=null;
		
		SelectDAO4 sDAO4=SelectDAO4.getInstance();
		modelList=sDAO4.selectAllModel();
		
		return modelList;
	} //searchAllModel
	
} //class
