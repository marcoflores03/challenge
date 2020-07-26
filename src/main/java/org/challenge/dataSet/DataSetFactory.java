package org.challenge.dataSet;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Fail;
import org.challenge.general.Constants;
import org.testng.Reporter;

public class DataSetFactory {

    private static final ImmutableMap<String, DataSet> dataSetCollection = ImmutableMap.<String, DataSet>builder()
            .put("environment", new EnvDataSet())
            .build();

    public static DataSet getDataSet(String requiredDataSet) {
        if(dataSetCollection.containsKey(requiredDataSet)){
            return dataSetCollection.get(requiredDataSet);
        } else {
            String warningText = "WARNING!. Test Method name not found in Data Set Collection, please check it.";
            System.out.println(Constants.ANSI_YELLOW + warningText + Constants.ANSI_RESET);
            Reporter.log(warningText);
            Fail.fail(warningText);
            return null;
        }
    }
}