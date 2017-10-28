//15003770
//Keegan Scannell
package com.ebookfrenzy.opsc7312_assign2_15003770;

/**
 * Created by keegz_000 on 2017/10/08.
 */

public class Details {

    //Declared Variables
    private String ChildName;
    private String ChildAge;
    private String ParentName;
    private String ParentNo;
    private String Time;

    public Details(String childName, String childAge, String parentName, String parentNo, String time) {
        ChildName = childName;
        ChildAge = childAge;
        ParentName = parentName;
        ParentNo = parentNo;
        Time = time;
    }

    //gets and sets
    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getChildAge() {
        return ChildAge;
    }

    public void setChildAge(String childAge) {
        ChildAge = childAge;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getParentNo() {
        return ParentNo;
    }

    public void setParentNo(String parentNo) {
        ParentNo = parentNo;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
