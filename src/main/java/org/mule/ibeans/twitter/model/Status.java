/*
 * $Id: Status.java 295 2011-01-24 06:39:14Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.twitter.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A object representation of the Status data used by Twitter.  This object can be used to bind to XMl and JSON
 */
@XmlAccessorType
public class Status
{
    @JsonProperty("in_reply_to_user_id")
    @XmlElement(name = "in_reply_to_user_id")
    private String inReplyToUserId;
    @JsonProperty("in_reply_to_screen_name")
    @XmlElement(name = "in_reply_to_screen_name")
    private String inReplyToScreenName;
    @JsonProperty("in_reply_to_status_id")
    @XmlElement(name = "in_reply_to_status_id")
    private String inReplyToStatusId;
    @JsonProperty("truncated")
    @XmlElement(name = "truncated")
    private boolean truncated;
    @JsonProperty("source")
    @XmlElement(name = "source")
    private String source;
    @JsonProperty("favorited")
    @XmlElement(name = "favorited")
    private boolean favorited;
    @JsonProperty("geo")
    @XmlElement(name = "geo")
    private String geo;
    @JsonProperty("created_at")
    @XmlElement(name = "created_at")
    private String createdAt;
    @JsonProperty("id")
    @XmlElement(name = "id")
    private String id;
    @JsonProperty("text")
    @XmlElement(name = "text")
    private String text;
    @JsonProperty("user")
    @XmlElement(name = "user")
    private User user;

    //New properties added Jan 1, 2010
    @JsonProperty("contributors")
    @XmlElement(name = "contributors")
    private String contributors;

    //New properties added April 1, 2010
    @JsonProperty("place")
    @XmlElement(name = "place")
    private String place;

    @JsonProperty("coordinates")
    @XmlElement(name = "coordinates")
    private String coordinates;

    //New Properties added May 1, 2010
    @JsonProperty("user_id")
    @XmlElement(name = "user_id")
    private String userId;

    //New Properties added Jan 23, 2011
    @JsonProperty
    private String in_reply_to_user_id_str;
    @JsonProperty
    private String id_str;
    @JsonProperty
    private String in_reply_to_status_id_str;

    @JsonProperty("retweeted")
    @XmlElement(name = "retweeted")
    private boolean retweeted;

    @JsonProperty("retweet_count")
    @XmlElement(name = "retweet_count")
    private int retweetCount;

    public int getRetweetCount()
    {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount)
    {
        this.retweetCount = retweetCount;
    }

    public boolean isRetweeted()
    {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted)
    {
        this.retweeted = retweeted;
    }



    public String getInReplyToUserId()
    {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId)
    {
        this.inReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToScreenName()
    {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName)
    {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public String getInReplyToStatusId()
    {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId)
    {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public boolean isTruncated()
    {
        return truncated;
    }

    public void setTruncated(boolean truncated)
    {
        this.truncated = truncated;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public boolean isFavorited()
    {
        return favorited;
    }

    public void setFavorited(boolean favorited)
    {
        this.favorited = favorited;
    }

    public String getGeo()
    {
        return geo;
    }

    public void setGeo(String geo)
    {
        this.geo = geo;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getContributors()
    {
        return contributors;
    }

    public void setContributors(String contributors)
    {
        this.contributors = contributors;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(String coordinates)
    {
        this.coordinates = coordinates;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
