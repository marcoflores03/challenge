package org.challenge.general;

import com.google.gson.*;
import org.challenge.dataSet.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHelper {

    public JsonObject getJsonObjectFromFile(final String path) {

        final JsonParser parser = new JsonParser();

        try {
            final Object object = parser.parse(new FileReader(path));
            return (JsonObject) object;
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataSet getDataSetFromJsonFile(final String filePath, final String dataSetType, final String value) {

        final Gson gson = new Gson();
        final String[] path = JsonHelper.class.getClassLoader().getResource(filePath).toString().split("file:");

        final JsonObject jsonData = getJsonObjectFromFile(path[1]);
        final JsonElement element = jsonData.get(value);

        DataSet dataSet = DataSetFactory.getDataSet(dataSetType);
        dataSet = gson.fromJson(element, dataSet.getClass());

        return dataSet;
    }

    public String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

    public static JsonObject fromStringToJsonObj(String body) {
        JsonParser parser = new JsonParser();
        Object object = parser.parse(body);
        return (JsonObject) object;
    }
}