package com.fray.evo.util;
/** 
 * The singleton instance contains all Zerg Units
 * @author Cyrik
 */
public final class ZergUnitLibrary extends Library<Unit> {
  public static final Unit Larva=ZergLibrary.Larva;
  public static final Unit Zergling=ZergLibrary.Zergling;
  public static final Unit Drone=ZergLibrary.Drone;
  public static final Unit Roach=ZergLibrary.Roach;
  public static final Unit Queen=ZergLibrary.Queen;
  public static final Unit Baneling=ZergLibrary.Baneling;
  public static final Unit Mutalisk=ZergLibrary.Mutalisk;
  public static final Unit Hydralisk=ZergLibrary.Hydralisk;
  public static final Unit Infestor=ZergLibrary.Infestor;
  public static final Unit Corruptor=ZergLibrary.Corruptor;
  public static final Unit Ultralisk=ZergLibrary.Ultralisk;
  public static final Unit Broodlord=ZergLibrary.Broodlord;
  public static final Unit Overlord=ZergLibrary.Overlord;
  public static final Unit Overseer=ZergLibrary.Overseer;
  /** 
 * initializes the lists
 */
  private ZergUnitLibrary(){
    super();
    libraryList.add(Drone);
    libraryList.add(Zergling);
    libraryList.add(Roach);
    libraryList.add(Queen);
    libraryList.add(Baneling);
    libraryList.add(Mutalisk);
    libraryList.add(Hydralisk);
    libraryList.add(Infestor);
    libraryList.add(Corruptor);
    libraryList.add(Broodlord);
    libraryList.add(Ultralisk);
    libraryList.add(Overlord);
    libraryList.add(Overseer);
    libraryList.add(Larva);
    for (    Unit unit : libraryList) {
      int genVar4415;
      genVar4415=unit.getId();
      idToItemMap.put(genVar4415,unit);
    }
  }
  private static final ZergUnitLibrary instance=new ZergUnitLibrary();
  public static final ZergUnitLibrary getInstance(){
    return instance;
  }
}