package com.parser;

import com.lexer.TokenType;

public enum NodeType {
  Comparison         ("Comparison"),
  BooleanOp          ("Boolean Operator"),
  NumberOp           ("Number Operator"),
  String             ("String"),
  Assignment         ("Assignment"),
  Control            ("Control"),
  IO                 ("IO"),
  Integer            ("Integer"),
  UserDefinedName    ("User Defined Name"),
  Procedure          ("Procedure"),
  Grouping           ("Grouping"),
  ShortString        ("Short String"),


  INSTR ("INSTR");

  public final String name;

  private NodeType(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }

  public static NodeType fromTokenType(TokenType token) {
    NodeType coercedType = IO;
    switch(token) {
      case Comparison       : coercedType = NodeType.Comparison;      break;
      case BooleanOp        : coercedType = NodeType.BooleanOp;       break;         
      case NumberOp         : coercedType = NodeType.NumberOp;        break;    
      case String           : coercedType = NodeType.String;          break;        
      case Assignment       : coercedType = NodeType.Assignment;      break;       
      case Control          : coercedType = NodeType.Control;         break;        
      case IO               : coercedType = NodeType.IO;              break;        
      case Integer          : coercedType = NodeType.Integer ;        break; 
      case UserDefinedName  : coercedType = NodeType.UserDefinedName; break;    
      case Procedure        : coercedType = NodeType.Procedure;       break;          
      case Grouping         : coercedType = NodeType.Grouping;        break;           
      case ShortString      : coercedType = NodeType.ShortString ;    break;
    }
    return coercedType;
  }
}