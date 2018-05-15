package com.example.rianamcbride.contacts;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.TextViewCompat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //TESTS THAT SHOULD PASS
    @Test
    public void saveOrUpdatePhoto_newTimeline_validInfo() {

        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.priority = 2;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertNotEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newScenefile_validInfo() {

        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertNotEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateScenefile_validInfo() {

        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.photoObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_validInfo() {

        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.timelineEntryObjectId = 1;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        metaDataVO.priority = 2;

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newTimeline_ExtraInfo(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.priority = 2;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        //unneeded information for new timeline
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertNotEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newScenefile_ExtraInfo(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        //extra timeline info
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertNotEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_ExtraInfo(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.timelineEntryObjectId = 1;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        metaDataVO.priority = 2;
        //extra info
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateScenefile_ExtraInfo(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.photoObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        Random random = new Random();
        random.nextBytes(photoBytes);
        //extra info
        metaDataVO.description = "Test";
        metaDataVO.priority = 2;

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_SendPhotoID(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.timelineEntryObjectId = 1;
        metaDataVO.photoObjectId = 1;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        metaDataVO.priority = 2;

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.assertEquals(0, result.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TESTS THAT SHOULD FAIL
    @Test
    public void saveOrUpdatePhoto_updateTimeline_setPriorityGreaterThanFour() {
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.timelineEntryObjectId = 1;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";
        metaDataVO.priority = 8;

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_IDGreaterThanZeroForScenefileAndTimeline_ShouldFail() {

        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.timelineEntryObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @Test
    public void saveOrUpdatePhoto_newTimeline_nullByteArray_ShouldFail(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = null;
        metaDataVO.priority = 2;
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_nullByteArray_ShouldFail() {
        MetaDataVO metaDataVO = new MetaDataVO();
        metaDataVO.timelineEntryObjectId = 1;
        byte[] photoBytes = null;
        metaDataVO.priority = 2;
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newScenefile_nullByteArray_ShouldFail() {
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = null;
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateScenefile_nullByteArray_ShouldFail() {
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = null;
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.photoObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_PhotoIDGreaterThanZero() {
        MetaDataVO metaDataVO = new MetaDataVO();
        metaDataVO.timelineEntryObjectId = 1;
        byte[] photoBytes = null;
        metaDataVO.priority = 2;
        metaDataVO.title = "Test";
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newTimeline_NoTitle(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.priority = 2;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = null;
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_newScenefile_NoTitle(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = null;
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateTimeline_NoTitle(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.timelineEntryObjectId = 1;
        metaDataVO.priority = 2;
        Random random = new Random();
        random.nextBytes(photoBytes);
        metaDataVO.title = null;
        metaDataVO.description = "Test";

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveOrUpdatePhoto_updateScenefile_NoTitle(){
        MetaDataVO metaDataVO = new MetaDataVO();
        byte[] photoBytes = new byte[10000];
        metaDataVO.sceneFileObjectId = 1;
        metaDataVO.photoObjectId = 1;
        metaDataVO.comment_SCENEFILE_BS = "Test";
        metaDataVO.date_SCENEFILE_BS = "May 15, 2018";
        metaDataVO.description_SCENEFILE_BS = "Test";
        metaDataVO.location_SCENEFILE_BS = "Test";
        metaDataVO.photographer_SCENEFILE_BS = "Test";
        metaDataVO.originalFileName_SCENEFILE_BS = "Test";
        metaDataVO.title_SCENEFILE_BS = null;
        Random random = new Random();
        random.nextBytes(photoBytes);

        try {
            String result = saveOrUpdatePhoto(photoBytes, metaDataVO);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String saveOrUpdatePhoto(byte[] bytes, MetaDataVO metadataVo) throws Exception {
        return null;
    }
}
