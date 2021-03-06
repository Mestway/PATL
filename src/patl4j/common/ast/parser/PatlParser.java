/* Generated By:JavaCC: Do not edit this line. PatlParser.java */
package patl4j.common.ast.parser;

import patl4j.common.ast.full.*;
import patl4j.common.ast.full.FullExpTail.*;
import patl4j.common.ast.pattern.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class PatlParser implements PatlParserConstants {
        static List<VarDecl> varDecls = new ArrayList<VarDecl>();

        public static void main(String[] args) throws ParseException {
                /*String testExample = "(x: A -> B, y: C->D) { m A a,b,c; m z =  x.m(a,b); - z = x.f; + new C(a, b); } (x: A -> B, y: C->D) { m z =  x.m(a,b); - z = x.f; + new C(a, b); }";
		List<Rule> p = new PatlParser(new java.io.StringReader(testExample)).Pi();
		for (Rule i : p) {
		  	System.out.println(i);
		}*/

                String testExample = "+ z=xx.f.e(e, b, c).g(a.t(a,b),x,y).e();";
                String eg2 = "(x: A -> B) { m x.getR();\u0009+ A x = x.getP().getSecond();}";
                Rule tl = new PatlParser(new java.io.StringReader(eg2)).pi();
                System.out.println(tl);
        }

  final public List<Rule> Pi() throws ParseException {
                    List<Rule> ruleList = new ArrayList<Rule>(); Rule r;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      r = pi();
              ruleList.add(r);
    }
                                     {if (true) return ruleList;}
    throw new Error("Missing return statement in function");
  }

  final public Rule pi() throws ParseException {
  List<VarDecl> decls = new ArrayList<VarDecl>();
  ModInstruction s;
  List<ModInstruction> instrs = new ArrayList<ModInstruction>();
    decls = decls();
                        varDecls = decls;
    jj_consume_token(LBRACE);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MOD:
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      s = i();
                                                           instrs.add(s);
    }
    jj_consume_token(RBRACE);
                {if (true) return new Rule(decls, instrs);}
    throw new Error("Missing return statement in function");
  }

  final public List<VarDecl> decls() throws ParseException {
        List<VarDecl> decls = new ArrayList<VarDecl>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      decls = DeclList();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
                {if (true) return decls;}
    throw new Error("Missing return statement in function");
  }

  final public List<VarDecl> DeclList() throws ParseException {
        VarDecl v;
        List<VarDecl> list = new ArrayList<VarDecl>();
    v = Declaration();
                            list.add(v);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      v = Declaration();
                                                                  list.add(v);
    }
                {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  final public VarDecl Declaration() throws ParseException {
        Token t1,t2,t3;
    t1 = jj_consume_token(IDENTIFIER);
    jj_consume_token(36);
    t2 = jj_consume_token(IDENTIFIER);
    jj_consume_token(ARROW);
    t3 = jj_consume_token(IDENTIFIER);
                {if (true) return new VarDecl(t1.toString(), t2.toString(), t3.toString());}
    throw new Error("Missing return statement in function");
  }

  final public ModInstruction i() throws ParseException {
        StatementPattern stmt;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
      stmt = fullStatement();
                {if (true) return new ModInstruction(stmt, "+");}
      break;
    case MINUS:
      jj_consume_token(MINUS);
      stmt = p();
                {if (true) return new ModInstruction(stmt, "-");}
      break;
    case MOD:
      jj_consume_token(MOD);
      stmt = p();
                {if (true) return new ModInstruction(stmt, "m");}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public StatementPattern p() throws ParseException {
        Token var;
        PEPattern pe;
        RHSPattern r;
    if (jj_2_1(2)) {
      var = jj_consume_token(IDENTIFIER);
      jj_consume_token(37);
      r = r();
      jj_consume_token(SEMICOLON);
                MetaVariable mVar = new MetaVariable(var.toString(), varDecls);
        {if (true) return new AssignStmtPattern(mVar, r);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEW:
      case IDENTIFIER:
        pe = e();
        jj_consume_token(SEMICOLON);
          {if (true) return new ExpStmtPattern(pe);}
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public RHSPattern r() throws ParseException {
        RHSPattern r;
        Token target;
        Token field;
        Token second;
        Token operator;
    if (jj_2_2(4)) {
      r = e();
                  {if (true) return r;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        target = jj_consume_token(IDENTIFIER);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
          jj_consume_token(DOT);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case IDENTIFIER:
            field = jj_consume_token(IDENTIFIER);
            break;
          case MOD:
            field = jj_consume_token(MOD);
            break;
          default:
            jj_la1[6] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
                MetaVariable tgt = new MetaVariable(target.toString(), varDecls);
                {if (true) return new FieldAccessPattern(tgt, field.toString());}
          break;
        case PLUS:
        case MINUS:
        case STAR:
        case SLASH:
        case BIT_AND:
        case BIT_OR:
        case XOR:
        case REM:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case PLUS:
            operator = jj_consume_token(PLUS);
            break;
          case MINUS:
            operator = jj_consume_token(MINUS);
            break;
          case SLASH:
            operator = jj_consume_token(SLASH);
            break;
          case STAR:
            operator = jj_consume_token(STAR);
            break;
          case BIT_AND:
            operator = jj_consume_token(BIT_AND);
            break;
          case BIT_OR:
            operator = jj_consume_token(BIT_OR);
            break;
          case XOR:
            operator = jj_consume_token(XOR);
            break;
          case REM:
            operator = jj_consume_token(REM);
            break;
          default:
            jj_la1[7] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case CONSTANT:
            second = jj_consume_token(CONSTANT);
            break;
          case IDENTIFIER:
            second = jj_consume_token(IDENTIFIER);
            break;
          default:
            jj_la1[8] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
                MetaVariable tgt2 = new MetaVariable(target.toString(), varDecls);
                MetaVariable snd = new MetaVariable(second.toString(), varDecls);
                {if (true) return new BinaryOperation(tgt2, snd, operator.toString());}
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public PEPattern e() throws ParseException {
        Token target;
        Token methodName;
        List<MetaVariable> argList = new ArrayList<MetaVariable>();
        Token className;
    if (jj_2_3(2)) {
      target = jj_consume_token(IDENTIFIER);
      jj_consume_token(DOT);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        methodName = jj_consume_token(IDENTIFIER);
        break;
      case MOD:
        methodName = jj_consume_token(MOD);
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      argList = Arguments();
                MetaVariable tgt = new MetaVariable(target.toString(), varDecls);
        {if (true) return new MethodPattern(tgt, methodName.toString(), argList);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEW:
        jj_consume_token(NEW);
        className = jj_consume_token(IDENTIFIER);
        argList = Arguments();
          {if (true) return new NewPattern(className.toString(), argList);}
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public List<MetaVariable> Arguments() throws ParseException {
  List<MetaVariable> argList=new ArrayList<MetaVariable>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      argList = ArgumentList();
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
        {if (true) return argList;}
    throw new Error("Missing return statement in function");
  }

  final public List<MetaVariable> ArgumentList() throws ParseException {
        List<MetaVariable> argList = new ArrayList<MetaVariable>();
        Token s;
    s = jj_consume_token(IDENTIFIER);
                           argList.add(new MetaVariable(s.toString(), varDecls));
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_4;
      }
      jj_consume_token(COMMA);
      s = jj_consume_token(IDENTIFIER);
                                                                                                           argList.add(new MetaVariable(s.toString(), varDecls));
    }
                {if (true) return argList;}
    throw new Error("Missing return statement in function");
  }

/*The following is full* series expressions etc for new client program*/
  final public FullStatement fullStatement() throws ParseException {
        FullStatement fs;
    if (jj_2_4(3)) {
      fs = fullAssignment();
                {if (true) return fs;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONSTANT:
      case STRING:
      case NEW:
      case IDENTIFIER:
        fs = fullExpressionStatement();
                {if (true) return fs;}
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public FullAssignment fullAssignment() throws ParseException {
        Token v;
        Token type;
        FullExpression fullexp;
    if (jj_2_5(3)) {
      v = jj_consume_token(IDENTIFIER);
      jj_consume_token(37);
      fullexp = fullExpression();
      jj_consume_token(SEMICOLON);
                {if (true) return new FullAssignment(v.toString(), fullexp);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        type = jj_consume_token(IDENTIFIER);
        v = jj_consume_token(IDENTIFIER);
        jj_consume_token(37);
        fullexp = fullExpression();
        jj_consume_token(SEMICOLON);
                {if (true) return new FullAssignment(type.toString(), v.toString(), fullexp);}
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public FullExpressionStatement fullExpressionStatement() throws ParseException {
        FullExpression fullexp;
    fullexp = fullExpression();
    jj_consume_token(SEMICOLON);
                {if (true) return new FullExpressionStatement(fullexp);}
    throw new Error("Missing return statement in function");
  }

  final public FullExpression fullExpression() throws ParseException {
        Token var;
        Token cn;
        List<FullExpression> args;
        FullExpTail tail = null;
        String s;
        List<String> operands = new ArrayList<String>();
        List<Boolean> operators = new ArrayList<Boolean>();
    if (jj_2_6(3)) {
      s = symbol();
                       operands.add(s);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
                                                      operators.add(new Boolean(true));
          break;
        case MINUS:
          jj_consume_token(MINUS);
                                                                                                      operators.add(new Boolean(false));
          break;
        default:
          jj_la1[17] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        s = symbol();
                                                                                                                                                           operands.add(s);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MINUS:
          ;
          break;
        default:
          jj_la1[18] = jj_gen;
          break label_5;
        }
      }
                {if (true) return new FullAddExpression(operands, operators);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        var = jj_consume_token(IDENTIFIER);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
          tail = fullExpTail();
          break;
        default:
          jj_la1[19] = jj_gen;
          ;
        }
                FullExpression fv = new FullVariable(var.toString());
                if (tail == null)
                        {if (true) return fv;}
                else
                        {if (true) return tail.toExpression(fv);}
        break;
      case NEW:
        jj_consume_token(NEW);
        cn = jj_consume_token(IDENTIFIER);
        args = fullArguments();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
          tail = fullExpTail();
          break;
        default:
          jj_la1[20] = jj_gen;
          ;
        }
                FullExpression fn = new FullNew(cn.toString(), args);
                if (tail == null)
                        {if (true) return fn;}
                else
                        {if (true) return tail.toExpression(fn);}
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public FullExpTail fullExpTail() throws ParseException {
        Token m;
        Token f;
        List<FullExpression> args;
        FullExpTail tail = null;
    if (jj_2_7(3)) {
      jj_consume_token(DOT);
      m = jj_consume_token(IDENTIFIER);
      args = fullArguments();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        tail = fullExpTail();
        break;
      default:
        jj_la1[22] = jj_gen;
        ;
      }
                FullExpTail tl = new FullExpTail();
                MethodBody mb = tl.new MethodBody(m.toString(), args);
                tl.set(mb, tail);
                {if (true) return tl;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        jj_consume_token(DOT);
        f = jj_consume_token(IDENTIFIER);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
          tail = fullExpTail();
          break;
        default:
          jj_la1[23] = jj_gen;
          ;
        }
                FullExpTail tl = new FullExpTail();
                FieldBody fb = tl.new FieldBody(f.toString());
                tl.set(fb, tail);
                {if (true) return tl;}
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public List<FullExpression> fullArguments() throws ParseException {
  List<FullExpression> args = new ArrayList<FullExpression>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CONSTANT:
    case STRING:
    case NEW:
    case IDENTIFIER:
      args = fullArgumentList();
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
        {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public List<FullExpression> fullArgumentList() throws ParseException {
        List<FullExpression> args = new ArrayList<FullExpression>();
        FullExpression s;
    s = fullExpression();
                               args.add(s);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[26] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      s = fullExpression();
                                                                          args.add(s);
    }
                {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public String symbol() throws ParseException {
        Token s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      s = jj_consume_token(IDENTIFIER);
      break;
    case CONSTANT:
      s = jj_consume_token(CONSTANT);
      break;
    case STRING:
      s = jj_consume_token(STRING);
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return s.toString();}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_3R_21() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(33)) {
    jj_scanpos = xsp;
    if (jj_scan_token(20)) {
    jj_scanpos = xsp;
    if (jj_scan_token(22)) return true;
    }
    }
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_12()) return true;
    return false;
  }

  private boolean jj_3R_20() {
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  private boolean jj_3R_19() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_20()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(37)) return true;
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3R_8() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) return true;
    }
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_scan_token(NEW)) return true;
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_15() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_3R_10()) return true;
    Token xsp;
    if (jj_3R_11()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_11()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_9() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(DOT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(33)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) return true;
    }
    if (jj_3R_10()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public PatlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[28];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x400,0x7000000,0x0,0x20000,0x7000000,0x800000,0x1000000,0xfe000000,0x100000,0xfe040000,0x0,0x1000000,0x800000,0x0,0x20000,0xd00000,0x0,0x6000000,0x6000000,0x40000,0x40000,0x800000,0x40000,0x40000,0x40000,0xd00000,0x20000,0x500000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x2,0x0,0x0,0x2,0x2,0x1,0x2,0x1,0x2,0x2,0x0,0x2,0x0,0x2,0x2,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x2,0x0,0x2,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[7];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public PatlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public PatlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PatlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public PatlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new PatlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public PatlParser(PatlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(PatlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[38];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 28; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 38; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 7; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
