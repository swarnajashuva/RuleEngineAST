package com.Project.ZeoTap.RuleEngineAST;

import org.json.JSONObject;

import com.Project.ZeoTap.RuleEngineAST.ZeoTapProject.Node;


public class App {
    public static void main( String[] args ){
    	       RuleEngine engine =new RuleEngine();

    	        // Test creating rules
    	        Node rule1 = engine.createRule("((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)");
    	        Node rule2 = engine.createRule("((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)");

    	        // Combine rules
    	        Node combinedRule = engine.combineRules(new Node[]{rule1, rule2});

    	        // Test evaluation
    	        JSONObject userData = new JSONObject();
    	        userData.put("age", 35);
    	        userData.put("department", "Sales");
    	        userData.put("salary", 60000);
    	        userData.put("experience", 3);

    	        boolean isEligible = engine.evaluateRule(combinedRule, userData);
    	        System.out.println("Is user eligible? " + isEligible); // Should print true or false based on rules
    	    
    }
}
