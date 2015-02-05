/* Generated By:JavaCC: Do not edit this line. PatlParserTokenManager.java */
package patl4j.patl.ast.parser;
import patl4j.patl.ast.*;
import java.util.List;
import java.util.ArrayList;

/** Token Manager. */
public class PatlParserTokenManager implements PatlParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x20L) != 0L)
            return 0;
         if ((active0 & 0x800000L) != 0L)
            return 5;
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 24;
            return 5;
         }
         return -1;
      case 1:
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 1;
            return 5;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 10);
      case 41:
         return jjStopAtPos(0, 11);
      case 43:
         return jjStopAtPos(0, 28);
      case 44:
         return jjStopAtPos(0, 17);
      case 45:
         jjmatchedKind = 29;
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 46:
         return jjStopAtPos(0, 18);
      case 47:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 58:
         return jjStopAtPos(0, 27);
      case 59:
         return jjStopAtPos(0, 16);
      case 61:
         return jjStopAtPos(0, 30);
      case 91:
         return jjStopAtPos(0, 14);
      case 93:
         return jjStopAtPos(0, 15);
      case 109:
         return jjStartNfaWithStates_0(0, 23, 5);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 123:
         return jjStopAtPos(0, 12);
      case 125:
         return jjStopAtPos(0, 13);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(1, 5);
         break;
      case 62:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(2, 19);
         break;
      case 119:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec1 = {
   0xfff0000000200002L, 0xffffffffffffdfffL, 0xfffff00f7fffffffL, 0x12000000007fffffL
};
static final long[] jjbitVec3 = {
   0x0L, 0x0L, 0x420043c00000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec4 = {
   0xffffcffffffffL, 0xffffffffffff0000L, 0xf9ff3fffffffffffL, 0x401f00030003L
};
static final long[] jjbitVec5 = {
   0x0L, 0x400000000000000L, 0xfffffffbffffd740L, 0xffffffcff7fffL
};
static final long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffffff003L, 0x33fffffffff199fL
};
static final long[] jjbitVec7 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0xffL, 0x707ffffff0000L
};
static final long[] jjbitVec8 = {
   0x7fffffe00000000L, 0xfffe0000000007ffL, 0xffffffffffffffffL, 0x1c000060002fffffL
};
static final long[] jjbitVec9 = {
   0x1ffffffd0000L, 0x0L, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec10 = {
   0x23ffffffffffffe0L, 0x3ff010000L, 0x3c5fdfffff99fe0L, 0xf0003b0000000L
};
static final long[] jjbitVec11 = {
   0x36dfdfffff987e0L, 0x1c00005e000000L, 0x23edfdfffffbafe0L, 0x100010000L
};
static final long[] jjbitVec12 = {
   0x23cdfdfffff99fe0L, 0x3b0000000L, 0x3bfc718d63dc7e0L, 0x0L
};
static final long[] jjbitVec13 = {
   0x3effdfffffddfe0L, 0x300000000L, 0x3effdfffffddfe0L, 0x340000000L
};
static final long[] jjbitVec14 = {
   0x3fffdfffffddfe0L, 0x300000000L, 0x2ffbfffffc7fffe0L, 0x7fL
};
static final long[] jjbitVec15 = {
   0x800dfffffffffffeL, 0x7fL, 0x200decaefef02596L, 0x3000005fL
};
static final long[] jjbitVec16 = {
   0x1L, 0x7fffffffeffL, 0xf00L, 0x0L
};
static final long[] jjbitVec17 = {
   0x6fbffffffffL, 0x3f0000L, 0xffffffff00000000L, 0x7fffffffff003fL
};
static final long[] jjbitVec18 = {
   0xffffffffffffffffL, 0xffffffff83ffffffL, 0xffffff07ffffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec19 = {
   0xffffffffffffff7fL, 0xffffffff3d7f3d7fL, 0x7f3d7fffffff3d7fL, 0xffff7fffff7f7f3dL
};
static final long[] jjbitVec20 = {
   0xffffffff7f3d7fffL, 0x7ffff7fL, 0xffffffff00000000L, 0x1fffffffffffffL
};
static final long[] jjbitVec21 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec22 = {
   0xffffffffffffffffL, 0x7f9fffffffffffL, 0xffffffff07fffffeL, 0x7ffffffffffL
};
static final long[] jjbitVec23 = {
   0x0L, 0x0L, 0xfffffffffffffL, 0x8000000L
};
static final long[] jjbitVec24 = {
   0xffffffff00000000L, 0xffffffffffffffL, 0x1ffffffffffL, 0x0L
};
static final long[] jjbitVec25 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffff0fffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec26 = {
   0xffffffff3f3fffffL, 0x3fffffffaaff3f3fL, 0x5fdfffffffffffffL, 0x1fdc1fff0fcf1fdcL
};
static final long[] jjbitVec27 = {
   0x8000000000000000L, 0x8000000000000001L, 0xffff00000000L, 0x0L
};
static final long[] jjbitVec28 = {
   0x3fbbd503e2ffc84L, 0xffffffff00000000L, 0xfL, 0x0L
};
static final long[] jjbitVec29 = {
   0x73e03fe000000e0L, 0xfffffffffffffffeL, 0xfffffffe601fffffL, 0x7fffffffffffffffL
};
static final long[] jjbitVec30 = {
   0xfffe1fffffffffe0L, 0xffffffffffffffffL, 0xffffff00007fffL, 0x0L
};
static final long[] jjbitVec31 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffffffL, 0x0L
};
static final long[] jjbitVec32 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec33 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x1fffL, 0x0L
};
static final long[] jjbitVec34 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffL, 0x0L
};
static final long[] jjbitVec35 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec36 = {
   0x5f7ffdffa0f8007fL, 0xffffffffffffffdbL, 0x3ffffffffffffL, 0xfffffffffff80000L
};
static final long[] jjbitVec37 = {
   0x3fffffffffffffffL, 0xffffffffffff0000L, 0xfffffffffffcffffL, 0xfff0000000000ffL
};
static final long[] jjbitVec38 = {
   0x18000000000000L, 0xffd702000000e000L, 0xffffffffffffffffL, 0x1fffffffffffffffL
};
static final long[] jjbitVec39 = {
   0x87fffffe00000010L, 0xffffffe007fffffeL, 0x7fffffffffffffffL, 0x631cfcfcfcL
};
static final long[] jjbitVec40 = {
   0x0L, 0x0L, 0x420043cffffffffL, 0xff7fffffff7fffffL
};
static final long[] jjbitVec41 = {
   0xffffffffffffffffL, 0x400000700007fffL, 0xfffffffbffffd740L, 0xffffffcff7fffL
};
static final long[] jjbitVec42 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffffff07bL, 0x33fffffffff199fL
};
static final long[] jjbitVec43 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0xbbfffffbfffe00ffL, 0x707ffffff0016L
};
static final long[] jjbitVec44 = {
   0x7fffffe00000000L, 0xffff03ff003fffffL, 0xffffffffffffffffL, 0x1fff3dff9fefffffL
};
static final long[] jjbitVec45 = {
   0xffff1fffffff8000L, 0x7ffL, 0x1ffffffffffffL, 0x0L
};
static final long[] jjbitVec46 = {
   0xf3ffffffffffffeeL, 0xffcfff1f3fffL, 0xd3c5fdfffff99feeL, 0xfffcfb080399fL
};
static final long[] jjbitVec47 = {
   0xd36dfdfffff987e4L, 0x1fffc05e003987L, 0xf3edfdfffffbafeeL, 0xffc100013bbfL
};
static final long[] jjbitVec48 = {
   0xf3cdfdfffff99feeL, 0xffc3b0c0398fL, 0xc3bfc718d63dc7ecL, 0xff8000803dc7L
};
static final long[] jjbitVec49 = {
   0xc3effdfffffddfeeL, 0xffc300603ddfL, 0xc3effdfffffddfecL, 0xffc340603ddfL
};
static final long[] jjbitVec50 = {
   0xc3fffdfffffddfecL, 0xffc300803dcfL, 0x2ffbfffffc7fffecL, 0xc0000ff5f847fL
};
static final long[] jjbitVec51 = {
   0x87fffffffffffffeL, 0x3ff7fffL, 0x3bffecaefef02596L, 0x33ff3f5fL
};
static final long[] jjbitVec52 = {
   0xc2a003ff03000001L, 0xfffe07fffffffeffL, 0x1ffffffffeff0fdfL, 0x40L
};
static final long[] jjbitVec53 = {
   0x3c7f6fbffffffffL, 0x3ff03ffL, 0xffffffff00000000L, 0x7fffffffff003fL
};
static final long[] jjbitVec54 = {
   0xffffffff7f3d7fffL, 0x3fe0007ffff7fL, 0xffffffff00000000L, 0x1fffffffffffffL
};
static final long[] jjbitVec55 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0x3ff080fffffL
};
static final long[] jjbitVec56 = {
   0xffffffff03ff7800L, 0xffffffffffffffL, 0x3ffffffffffL, 0x0L
};
static final long[] jjbitVec57 = {
   0x80007c000000f000L, 0x8000fc0000000001L, 0xffff00000000L, 0x21fff0000L
};
static final long[] jjbitVec58 = {
   0x73efffe000000e0L, 0xfffffffffffffffeL, 0xfffffffe661fffffL, 0x7fffffffffffffffL
};
static final long[] jjbitVec59 = {
   0x5f7ffdffe0f8007fL, 0xffffffffffffffdbL, 0x3ffffffffffffL, 0xfffffffffff80000L
};
static final long[] jjbitVec60 = {
   0x18000f00000000L, 0xffd702000000e000L, 0xffffffffffffffffL, 0x9fffffffffffffffL
};
static final long[] jjbitVec61 = {
   0x87fffffe03ff0010L, 0xffffffe007fffffeL, 0x7fffffffffffffffL, 0xe0000631cfcfcfcL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 6;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     jjCheckNAdd(3);
                  }
                  else if (curChar == 36)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAdd(5);
                  }
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 0:
                  if (curChar != 47)
                     break;
                  if (kind > 9)
                     kind = 9;
                  jjCheckNAdd(1);
                  break;
               case 1:
                  if ((0xfffffffffffffbffL & l) == 0L)
                     break;
                  if (kind > 9)
                     kind = 9;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAdd(3);
                  break;
               case 4:
                  if (curChar != 36)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0x3ff00100fffc1ffL & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               case 1:
                  if (kind > 9)
                     kind = 9;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 5:
                  if ((0x87fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               case 1:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 9)
                     kind = 9;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 5:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(5);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 6 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      case 47:
         return jjMoveStringLiteralDfa1_1(0x40L);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec0[i2] & l2) != 0L);
      default :
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec3[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 7:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec13[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec14[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec15[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 18:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 19:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 20:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 22:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 23:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 24:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 77:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 164:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec39[i2] & l2) != 0L);
      default :
         if ((jjbitVec1[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec40[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec41[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec42[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec43[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec44[i2] & l2) != 0L);
      case 7:
         return ((jjbitVec45[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec46[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec47[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec48[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec49[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec50[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec51[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec52[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec53[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 18:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 19:
         return ((jjbitVec54[i2] & l2) != 0L);
      case 20:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 22:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 23:
         return ((jjbitVec55[i2] & l2) != 0L);
      case 24:
         return ((jjbitVec56[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec57[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec58[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 77:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 164:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec59[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec60[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec61[i2] & l2) != 0L);
      default :
         if ((jjbitVec1[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, "\50", "\51", 
"\173", "\175", "\133", "\135", "\73", "\54", "\56", "\55\76\76", null, null, 
"\156\145\167", "\155", null, null, null, "\72", "\53", "\55", "\75", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x79dffc01L, 
};
static final long[] jjtoSkip = {
   0x3feL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[6];
static private final int[] jjstateSet = new int[12];
private static final StringBuilder jjimage = new StringBuilder();
private static StringBuilder image = jjimage;
private static int jjimageLen;
private static int lengthOfMatch;
static protected char curChar;
/** Constructor. */
public PatlParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public PatlParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 6; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 2 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 8)
       {
          jjmatchedKind = 8;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
           SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 7 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
               SwitchTo(DEFAULT);
         break;
      default :
         break;
   }
}
static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
