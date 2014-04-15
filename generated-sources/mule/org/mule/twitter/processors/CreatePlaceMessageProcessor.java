
package org.mule.twitter.processors;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.security.oauth.callback.ProcessCallback;
import org.mule.twitter.TwitterConnector;
import org.mule.twitter.connectivity.TwitterConnectorConnectionManager;
import twitter4j.Place;


/**
 * CreatePlaceMessageProcessor invokes the {@link org.mule.twitter.TwitterConnector#createPlace(java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.lang.String)} method in {@link TwitterConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-04-15T10:53:47-05:00", comments = "Build M4.1875.17b58a3")
public class CreatePlaceMessageProcessor
    extends AbstractConnectedProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object placeName;
    protected String _placeNameType;
    protected Object containedWithin;
    protected String _containedWithinType;
    protected Object token;
    protected String _tokenType;
    protected Object latitude;
    protected Double _latitudeType;
    protected Object longitude;
    protected Double _longitudeType;
    protected Object streetAddress;
    protected String _streetAddressType;

    public CreatePlaceMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets streetAddress
     * 
     * @param value Value to set
     */
    public void setStreetAddress(Object value) {
        this.streetAddress = value;
    }

    /**
     * Sets token
     * 
     * @param value Value to set
     */
    public void setToken(Object value) {
        this.token = value;
    }

    /**
     * Sets containedWithin
     * 
     * @param value Value to set
     */
    public void setContainedWithin(Object value) {
        this.containedWithin = value;
    }

    /**
     * Sets longitude
     * 
     * @param value Value to set
     */
    public void setLongitude(Object value) {
        this.longitude = value;
    }

    /**
     * Sets placeName
     * 
     * @param value Value to set
     */
    public void setPlaceName(Object value) {
        this.placeName = value;
    }

    /**
     * Sets latitude
     * 
     * @param value Value to set
     */
    public void setLatitude(Object value) {
        this.latitude = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(TwitterConnectorConnectionManager.class, false, event);
            final String _transformedPlaceName = ((String) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_placeNameType").getGenericType(), null, placeName));
            final String _transformedContainedWithin = ((String) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_containedWithinType").getGenericType(), null, containedWithin));
            final String _transformedToken = ((String) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_tokenType").getGenericType(), null, token));
            final Double _transformedLatitude = ((Double) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_latitudeType").getGenericType(), null, latitude));
            final Double _transformedLongitude = ((Double) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_longitudeType").getGenericType(), null, longitude));
            final String _transformedStreetAddress = ((String) evaluateAndTransform(getMuleContext(), event, CreatePlaceMessageProcessor.class.getDeclaredField("_streetAddressType").getGenericType(), null, streetAddress));
            Object resultPayload;
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((TwitterConnector) object).createPlace(_transformedPlaceName, _transformedContainedWithin, _transformedToken, _transformedLatitude, _transformedLongitude, _transformedStreetAddress);
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(Place.class)));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(TwitterConnectorConnectionManager.class, true, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at TwitterConnector at createPlace retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
