package com.fray.evo.action.build;
import java.io.Serializable;
import com.fray.evo.util.ZergUnitLibrary;
public final class EcActionBuildBaneling extends EcActionBuildUnit implements Serializable {
  private static final long serialVersionUID=1L;
  public EcActionBuildBaneling(){
    super(ZergUnitLibrary.Baneling);
  }
}
