/*
 * $Id: TwitterTestCase.java 297 2011-04-12 00:12:01Z dzapata $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.twitter;

import org.mule.ibeans.test.ExternalPropsIBeansTestSupport;
import org.mule.ibeans.test.IBeansRITestSupport;
import org.mule.ibeans.twitter.model.Status;
import org.mule.module.json.JsonData;

import java.beans.ExceptionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.abdera.model.Feed;
import org.ibeans.annotation.IntegrationBean;
import org.ibeans.api.CallException;
import org.ibeans.impl.view.TextView;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mule.ibeans.IBeansSupport.selectValue;


/**
 * Tests a simple Twitter client that can update statuses. Switch between JSON and XML
 */
public class TwitterTestCase extends ExternalPropsIBeansTestSupport
{
    public static final String SEARCH_TERM = "ibeans";
    public static final String BAD_STATUS_ID = "-1";
    public static final String GOOD_STATUS_ID = "2837116608";
    public static final String STATUS_TEXT = "test from iBeans";
    public static final String STATUS_TEXT_XML = "xml " + STATUS_TEXT;
    public static final String STATUS_TEXT_JSON = "json " + STATUS_TEXT;

    @IntegrationBean
    private TwitterIBean twitter;

    protected TwitterIBean getTwitter()
    {
        return twitter;
    }

    @Before
    public void init()
    {
    	getTwitter().initOAuth("${twitter.consumer.key}", "${twitter.consumer.secret}");
    	getTwitter().setAccessToken("${twitter.access.token}", "${twitter.access.secret}");
    }

//    @Test
//    public void twitterUpdateProfileImageWithURL() throws Exception
//    {
//        getTwitter().setFormat(TwitterIBean.FORMAT.XML);
//        //Test using a URL, we'll test a File using the updateProfileBackgroundImage
//        URL url = ClassUtils.getResource("profile.png", getClass());
//
//        assertNotNull(url);
//        //Just make sure we don't get an error
//        getTwitter().updateProfileImage(url);
//    }

//    @Test
//    public void twitterUpdateProfileBackgroundImageWithFile() throws Exception
//    {
//        getTwitter().setFormat(TwitterIBean.FORMAT.XML, Document.class);
//        //Just test the executes
//        File f = new File("/projects/ibeans-contrib/twitter/src/test/resources/profile.png");
//
//        assertTrue(f.exists());
//        Document result = getTwitter().updateProfileBackgroundImage(f, true);
//
//        assertEquals("true", selectValue("/user/profile_background_tile", result));
//
//        //Test that the optional tile param can be null
//        getTwitter().updateProfileBackgroundImage(f, null);
//
//        try
//        {
//            getTwitter().updateProfileBackgroundImage((URL) null, null);
//            fail("image param cannot be null");
//        }
//        catch (IllegalArgumentException e)
//        {
//            //expected
//        }
//    }

    @Test
    public void statusUpdateJson() throws Exception
    {
        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        JsonData json = getTwitter().statusesUpdate(STATUS_TEXT_JSON + " " + generateId());
        assertNotNull(json);

        //assertEquals(STATUS_TEXT_JSON, json.get("text"));
        assertEquals("iBeans Test Account", json.getAsString("user/name"));
    }

    @Test
    public void twitterFriendTimeline() throws Exception
    {
        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        JsonData data = getTwitter().getFriendsTimeline(5, null, null, null);
        assertNotNull(data);
        assertTrue(data.isArray());

        assertNotNull(data.get("[0]/text"));
        assertNotNull(data.get("[0]/source"));
    }

    @Test
    public void twitterPublicTimeline() throws Exception
    {
        //TODO getTwitter().setCredentials(null, null);
        //Make sure public timeline works with defaults
        String string = getTwitter().getPublicTimeline();
        assertNotNull(string);

        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        //should return 20 entries
        JsonData data = getTwitter().getPublicTimeline();
        assertNotNull(data);
        assertTrue(data.isArray());

        assertNotNull(data.get("[0]/text"));
        assertNotNull(data.get("[12]/user/name"));
    }

    @Test
    public void twitterShowWithoutAuthentication() throws Exception
    {
        getTwitter().setAccessToken(null, null);

        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        //This doesn't require authentication.  iBeans should also automatically switch to HTTP GET
        JsonData data = getTwitter().statusesShow(GOOD_STATUS_ID);
        assertNotNull(data);
        assertEquals("Ross Mason", data.getAsString("user/name"));
    }

    @Test
    public void twitterStatusUpdateXML() throws Exception
    {
        //Twitter doesn't return the status you sent but the last status it has for
        //you on your account, so if there a delay, messages have a lag
        getTwitter().setFormat(TwitterBase.FORMAT.XML, Document.class);
        Document doc = getTwitter().statusesUpdate(STATUS_TEXT_XML + " " + generateId());
        assertNotNull(doc);

        assertTrue(selectValue("/status/text", doc).startsWith(STATUS_TEXT_XML));
        assertEquals("iBeans Test Account", selectValue("/status/user/name", doc));
    }

    @Test(expected = CallException.class)
    public void twitterJsonWithError() throws Exception
    {
        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        //This doesn't require authentication.  iBeans should also automatically switch to HTTP GET
        getTwitter().statusesShow(BAD_STATUS_ID);
        fail("An exception should have been thrown because the status does not exist");
    }

    @Test
    public void twitterJsonWithErrorListener() throws Exception
    {
        final AtomicBoolean exceptionThrown = new AtomicBoolean(false);
        getTwitter().setFormat(TwitterBase.FORMAT.JSON, JsonData.class);
        getTwitter().setExceptionListener(new ExceptionListener()
        {
            public void exceptionThrown(Exception e)
            {
                exceptionThrown.set(true);
            }
        });
        //This doesn't require authentication.  iBeans should also automatically switch to HTTP GET
        JsonData data = getTwitter().statusesShow(BAD_STATUS_ID);
        //Exception should not be thrown, instead the listener intercepts it
        assertTrue(exceptionThrown.get());

    }

    //Just test that the view generator works
    @Test
    public void usageView() throws Exception
    {
        TextView view = new TextView();
        String string = view.createView(TwitterIBean.class);
        System.out.println(string);
    }

    @Test
    public void twitterUpdateProfile() throws Exception
    {
        getTwitter().setFormat(TwitterIBean.FORMAT.XML, Document.class);
        Document result = getTwitter().updateProfile(null, "http://mulesoft.org/ibeans", "Malta", null);
        assertEquals("http://mulesoft.org/ibeans", selectValue("/user/url", result));
        assertEquals("Malta", selectValue("/user/location", result));
        assertEquals("iBeans Test Account", selectValue("/user/name", result));

        result = getTwitter().updateProfile(null, "http://mulesoft.org/display/IBEANS", "London", null);
        assertEquals("http://mulesoft.org/display/IBEANS", selectValue("/user/url", result));
        assertEquals("London", selectValue("/user/location", result));
    }

//    @Test
//    public void twitterTrends() throws Exception
//    {
//        
//    	
//    	getTwitter().trendsCurrent(null);
//        getTwitter().trendsDaily(null, null);
//        getTwitter().trendsWeekly(null, null);
//    }

    @Test
    public void twitterSearchWithAtom() throws Exception
    {
        getTwitter().setAccessToken(null, null);

        getTwitter().setFormat(TwitterIBean.FORMAT.ATOM, Feed.class);
        Feed result = getTwitter().search(SEARCH_TERM);
        assertNotNull(result);
        assertTrue(result.getEntries().size() > 0);

    }

    @Test
    public void twitterJsonBindings() throws Exception
    {
        getTwitter().setFormat(TwitterBase.FORMAT.JSON, Status.class);
        Status status = getTwitter().statusesUpdate(STATUS_TEXT_JSON + " " + generateId());
        assertNotNull(status);

        assertEquals("iBeans Test Account", status.getUser().getName());
    }

    //We get a 403 forbidden error, likely related to IBEANS-150

//    @Test
//    public void twitterXmlBindings() throws Exception
//    {
//        getTwitter().setFormat(TwitterBase.FORMAT.XML, Status.class);
//        Status status = getTwitter().statusesUpdate(STATUS_TEXT_XML);
//        assertNotNull(status);
//
//        //assertEquals(STATUS_TEXT_XML, json.get("text"));
//        assertEquals("iBeans Test Account", status.getUser().getName());
//    }
}
