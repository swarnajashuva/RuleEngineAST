package com.Project.ZeoTap.RuleEngineAST;


import org.json.JSONObject;

import com.Project.ZeoTap.RuleEngineAST.ZeoTapProject.Node;

class RuleEngine
{
    
    public Node createRule(String ruleString) 
    {
       
        return parseRule(ruleString);
    }

    private Node parseRule(String rule) 
    {
        if (rule.contains("AND")) 
        {
            String[] parts = rule.split("AND");
            Node node = new Node("operator", "AND", null);
            node.left = parseRule(parts[0].trim());
            node.right = parseRule(parts[1].trim());
            return node;
        }
        else if (rule.contains("OR"))
        {
            String[] parts = rule.split("OR");
            Node node = new Node("operator", "OR", null);
            node.left = parseRule(parts[0].trim());
            node.right = parseRule(parts[1].trim());
            return node;
        } else {
            return new Node("operand", null, rule.trim());
        }
    }

    public Node combineRules(Node[] rules) 
    {
        Node root = new Node("operator", "AND", null); // Combine using AND for simplicity
        root.left = rules[0];
        for (int i = 1; i < rules.length; i++) 
        {
            root.right = rules[i];
        }
        return root;
    }

    public boolean evaluateRule(Node rule, JSONObject data) {
        if (rule.type.equals("operand")) {
            return evaluateCondition(rule.value, data);
        } else {
            boolean leftResult = evaluateRule(rule.left, data);
            boolean rightResult = evaluateRule(rule.right, data);
            return rule.operator.equals("AND") ? leftResult && rightResult : leftResult || rightResult;
        }
    }

    private boolean evaluateCondition(String condition, JSONObject data) {
        // Basic evaluation logic (e.g., "age > 30")
        String[] parts = condition.split(" ");
        String attribute = parts[0];
        String operator = parts[1];
        int value = Integer.parseInt(parts[2]);
        
        if (!data.has(attribute)) return false;

        int attributeValue = data.getInt(attribute);
        switch (operator) {
            case ">":
                return attributeValue > value;
            case "<":
                return attributeValue < value;
            case "=":
                return attributeValue == value;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
