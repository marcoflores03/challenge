package org.challenge.dataSet;

/**
 * Base model of a bodies set needed to parse Json into Java Objects
 */

public class DataSet {
    public DataSet() {
        // No args constructor
    }

    ///region General Properties
    private String testCaseID;
    private String testCaseDescription;
    ///endregion

    //region Getters
    public String getTestCaseID() {
        return testCaseID;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }
    ///endregion
}