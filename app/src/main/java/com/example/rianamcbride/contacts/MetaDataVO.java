package com.example.rianamcbride.contacts;

class MetaDataVO {
    public int timelineEntryObjectId = -1;
    public int sceneFileObjectId = -1;
    public int photoObjectId = -1;

    //must set if new
    public String title;
    public String address;
    public String weather;
    public String description;
    public int priority;


    public String title_SCENEFILE_BS;
    public String originalFileName_SCENEFILE_BS;
    public String date_SCENEFILE_BS;
    public String location_SCENEFILE_BS;
    public String photographer_SCENEFILE_BS;
    public String description_SCENEFILE_BS;
    public String comment_SCENEFILE_BS;



    public int rotation;
    public int mediaCreateType;
    public double latitude;
    public double longitude;



    public void populateLocationInfo() {
        address = "123 Front St";
        weather = "Sunny";
        latitude = 43.642256;
        longitude = -79.731338;
        location_SCENEFILE_BS = "123 Main St";
    }
}
