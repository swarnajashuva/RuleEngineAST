package com.Project.ZeoTap.RuleEngineAST.ZeoTapProject;

public class Node 
{
		
		    public String type;
		    public String operator;
		    public  String value; 
		    public Node left; 
		    public Node right; 

		    public Node(String type, String operator, String value) 
		    {
		        this.type = type;
		        this.operator = operator;
		        this.value = value;
		        this.left = null;
		        this.right = null;
		    }

}
