//
// THIS FILE IS AUTOMATICALLY GENERATED!!
//
// Generated at 2017-01-04 by the VDM++ to JAVA Code Generator
// (v9.0.7 - Thu 29-Sep-2016 22:13:23 +0900)
//
// ***** VDMTOOLS START Name=HeaderComment KEEP=NO
// ***** VDMTOOLS END Name=HeaderComment

// ***** VDMTOOLS START Name=package KEEP=NO
package quotes;
// ***** VDMTOOLS END Name=package

// ***** VDMTOOLS START Name=imports KEEP=NO
// ***** VDMTOOLS END Name=imports



public class Submarine {

// ***** VDMTOOLS START Name=hc KEEP=NO
  static private int hc = 0;
// ***** VDMTOOLS END Name=hc

// ***** VDMTOOLS START Name=Submarine KEEP=NO
  public Submarine () {
    if (hc == 0) 
      hc = super.hashCode();
  }
// ***** VDMTOOLS END Name=Submarine

// ***** VDMTOOLS START Name=hashCode KEEP=NO
  public int hashCode () {
    return hc;
  }
// ***** VDMTOOLS END Name=hashCode

// ***** VDMTOOLS START Name=equals#1|Object KEEP=NO
  public boolean equals (Object obj) {
    return obj instanceof Submarine;
  }
// ***** VDMTOOLS END Name=equals#1|Object

// ***** VDMTOOLS START Name=toString KEEP=NO
  public String toString () {
    return "<Submarine>";
  }
// ***** VDMTOOLS END Name=toString

}

