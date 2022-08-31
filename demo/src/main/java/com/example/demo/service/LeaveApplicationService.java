package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.LeaveStatus;
import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.LeaveApplicationNativeSqlRepo;
import com.example.demo.repositories.LeaveApplicationRepository;


@Service
public class LeaveApplicationService {

	@Autowired
	LeaveApplicationRepository larepo;
	
	@Autowired
    private LeaveApplicationNativeSqlRepo leaveManageNativeRepo;
	@Autowired
	EmployeeRepository emrepo;
	
	@SuppressWarnings("deprecation")
	public void applyLeave(LeaveApplication leaveapplication)
	{
		int duration=leaveapplication.getToDate().getDate()-leaveapplication.getFromDate().getDate();
		leaveapplication.setDuration(duration+1);
		leaveapplication.setActive(true);
		larepo.save(leaveapplication);
	}
	public List<LeaveApplication> getAllLeaves() {

		return larepo.findAll();
	    }

	    public LeaveApplication getLeaveDetailsOnId(int id) {

		return larepo.findById(id);
	    }

	    public void updateLeave(LeaveApplication leaveapplication) {

	    	int duration=leaveapplication.getToDate().getDate()-leaveapplication.getFromDate().getDate();
			leaveapplication.setDuration(duration+1);
			leaveapplication.setActive(true);
	    	larepo.save(leaveapplication);

	    }
	    public void processLeave(LeaveApplication leaveapplication) {


	    	larepo.saveAndFlush(leaveapplication);

	    }

	    public List<LeaveApplication> getAllActiveLeaves() {

		return larepo.getAllActiveLeaves();
	    }

	    public List<LeaveApplication> getAllLeavesOfUser(String username) {

		return larepo.getAllLeavesOfUser(username);

	    }

	    public List<LeaveApplication> getAllLeavesOnStatus(boolean pending, boolean accepted, boolean rejected) {

		StringBuffer whereQuery = new StringBuffer();
		if (pending)
		    whereQuery.append("active=true or ");
		if (accepted)
		    whereQuery.append("(active=false and accept_reject_flag=true) or ");
		if (rejected)
		    whereQuery.append("(active=false and accept_reject_flag=false) or ");

		whereQuery.append(" 1=0");
		
		return leaveManageNativeRepo.getAllLeavesOnStatus(whereQuery);
	    }
	
	public List<LeaveApplication> listAll()
	{
		return larepo.findAll();	
	}
	public void addLeaveApplication(LeaveApplication leaveapplication)
	{
		larepo.save(leaveapplication);
	}
//	public void cancelLeaveApplication(LeaveApplication leaveapplication)
//	{
//		leaveapplication.setLeaveType(LeaveStatus.CANCELLED);
//		larepo.save(leaveapplication);
////	}
//	public boolean saveLeaveApplication(LeaveApplication leaveapplication)
//	{
//		if(larepo.save(leaveapplication)!=null)
//			return true;
//		else 
//			return false;
//	}
	public LeaveApplication findApplicationById(int id)
	{
		return larepo.findById(id);
	}
	public List<LeaveApplication> findApplicationByEmployeeId(String id)
	{
		return larepo.findByEmployeeName(id);
	}
	public String findManagerName()
	{
		return larepo.findManagerName();
	}
	public List<LeaveApplication> getAllNonActiveLeavesViaManager(String managername)
	{
		return larepo.getAllNonActiveLeavesViaManager(managername);
	}
	public List<LeaveApplication> getAllActiveLeavesViaManager(String managername)
	{
		return larepo.getAllActiveLeavesViaManager(managername);
	}
//	public List<LeaveApplication> findpendingleaveapproval(String maid)
//	{
//		List<Employee> listofemployee=emrepo.findByManagerId(maid);
//		
//		List<LeaveApplication> AllEmployeeLeave= new ArrayList();
//		for(Iterator iterator = listofemployee.iterator();iterator.hasNext();)
//		{
//			Employee employee=(Employee) iterator.next();
//			AllEmployeeLeave.addAll(larepo.findByStaffId(employee.getEmployeeId()));
//			
//		}
//		List<LeaveApplication> PendingLeave=new ArrayList();
//		for(Iterator iterator=AllEmployeeLeave.iterator();iterator.hasNext();)
//		{
//			LeaveApplication leave=(LeaveApplication) iterator.next();
//			if(leave.getLeaveStatus()==LeaveStatus.APPLIED)
//			{
//				PendingLeave.add(leave);
//			}
//		}
//		return PendingLeave;
//	}
//	public void approveleaveapplication(Integer id)
//	{
//		LeaveApplication leaveapplication=larepo.findById(id).get();
//		leaveapplication.setLeaveType(LeaveStatus.APPROVED);
//		larepo.save(leaveapplication);
//	}
//	public void rejectleaveapplication(Integer id)
//	{
//		LeaveApplication leaveapplication=larepo.findById(id).get();
//		leaveapplication.setLeaveType(LeaveStatus.REJECTED);
//		larepo.save(leaveapplication);
//	}
//	public void updateleaveapplication(int LeaveId,LeaveStatus status,String mComment)
//	{
//		LeaveApplication currLeaveApp=larepo.findById(LeaveId);
//		if(status.toString().equals("APPROVED"))
//		{
//			currLeaveApp.setLeaveType(LeaveStatus.APPROVED);
//		}
//		if(status.toString().equals("REJECTED"))
//		{
//			currLeaveApp.setLeaveType(LeaveStatus.REJECTED);
//		}
//		currLeaveApp.setManagerComment(mComment);
//		larepo.save(currLeaveApp);
//		
//	}
//	public void deleteLeaceApplication(LeaveApplication leaveApplication)
//	{
//		larepo.delete(leaveApplication);
//	}
//	public void deleteLeaveApplication(Integer id)
//	{
//		LeaveApplication leaveApplication=larepo.findById(id).get();
//		if(leaveApplication.getLeaveStatus()==LeaveStatus.APPLIED)
//		{
//			leaveApplication.setLeaveType(LeaveStatus.DELETED);
//			larepo.save(leaveApplication);
//		}
//	}
//	public boolean isWithinDateRange(Date currStartDate,Date currEndDate,Date testStartDate,Date testEndDate)
//	{
//		if(testStartDate.equals(currStartDate)&&testEndDate.equals(currEndDate))
//		{
//			return true;
//		}
//		if(testStartDate.after(currStartDate)&&testEndDate.before(currEndDate))
//		{
//			return true;
//		}
//		return false;
//	}
//	public ArrayList<LeaveApplication> findApplicationByManagerId(String managerId)
//	{
//		List<Employee> listofemployee=emrepo.findByManagerId(managerId);
//		
//		ArrayList<LeaveApplication> allEmployeeLeave=new ArrayList<LeaveApplication>();
//		for(Iterator<Employee> iterator=listofemployee.iterator();iterator.hasNext();)
//		{
//			Employee employee=(Employee) iterator.next();
//			allEmployeeLeave.addAll(larepo.findByStaffId(employee.getEmployeeId()));
//		}
//		
//		ArrayList<LeaveApplication> LeaveListExceptCancelAndReject= new ArrayList<LeaveApplication>();
//		for(Iterator<LeaveApplication> iterator=allEmployeeLeave.iterator();iterator.hasNext();)
//		{
//			LeaveApplication la=(LeaveApplication)iterator.next();
//			if(la.getLeaveStatus()!=LeaveStatus.CANCELLED && la.getLeaveStatus()!=LeaveStatus.REJECTED)
//			{
//				LeaveListExceptCancelAndReject.add(la);
//			}
//		}
//		return LeaveListExceptCancelAndReject;
//	}
//	public ArrayList<LeaveApplication> findSubLeaveAppByManagerId(String managerId)
//	{
//		List<Employee> listofemployee=emrepo.findByManagerId(managerId);
//		
//		ArrayList<LeaveApplication> allEmployeeLeave=new ArrayList<LeaveApplication>();
//		for(Iterator<Employee> iterator = listofemployee.iterator();iterator.hasNext();)
//		{
//			Employee employee=(Employee) iterator.next();
//			allEmployeeLeave.addAll(larepo.findByStaffId(employee.getEmployeeId()));
//		}
//		return allEmployeeLeave;
//	}
//	public boolean validateforCancel(LeaveApplication leaveApp)
//	{
//		if(leaveApp.getLeaveStatus()==LeaveStatus.APPROVED)
//		{
//			Calendar c=Calendar.getInstance();
//			c.set(Calendar.HOUR_OF_DAY, 0);
//			c.set(Calendar.MINUTE, 0);
//			c.set(Calendar.SECOND, 0);
//			c.set(Calendar.MILLISECOND, 0);
//			
//			Date startDate=leaveApp.getStartDate();
//			Date today=c.getTime();
//			if(startDate.after(today) || startDate.compareTo(today)!=0)
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	public ArrayList<LeaveApplication> findApplicationsExCancelDeleteReject(String employeeId) {
//		return larepo.findApplicationsExCancelDeleteReject(employeeId);
//	}
//	
	public void delete(Integer id)
	{
		larepo.deleteById(id);
	}
	public LeaveApplication changeUser(LeaveApplication leaveapplication) {
		return larepo.saveAndFlush(leaveapplication);
	}
	
}
