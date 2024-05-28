package project.service;

import project.model.Plan;
import project.model.Policy;

public class ViewPlan {

	// 필드 
	
	private Plan projectPlan ; /* 프로젝트 상세설명  */ 
	private Policy policy ; /* 환불 정책 */
	
	
	// 생성자 
	public ViewPlan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ViewPlan(Plan projectPlan, Policy policy) {
		super();
		this.projectPlan = projectPlan;
		this.policy = policy;
	}

	// getter, setter 
	public Plan getProjectPlan() {
		return projectPlan;
	}

	public void setProjectPlan(Plan projectPlan) {
		this.projectPlan = projectPlan;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	} 
	
	
	
	
	
	
	
}
