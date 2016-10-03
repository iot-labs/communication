package org.iotlabs.communication.mqtt.clients.publisher;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.iotlabs.communication.mqtt.clients.BaseClient;

import java.util.UUID;

public class PublisherPool {
    private static Logger logger = Logger.getLogger(PublisherPool.class);

    private ObjectPool<BaseClient> publisherObjectPool;

    /**
     * Create publisher pool
     * @param poolSize pool size
     * @param brokerUrl publisher broker url
     * @param isSync should create sync publisher or async publisher
     */
    public PublisherPool(int poolSize, String brokerUrl, boolean isSync) {

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(poolSize);
        genericObjectPoolConfig.setMinIdle(poolSize);
        genericObjectPoolConfig.setMaxIdle(poolSize);

        this.publisherObjectPool =
                new GenericObjectPool<BaseClient>(
                        new PublisherPoolFactory(brokerUrl, isSync),
                        genericObjectPoolConfig
                );
    }

    /**
     * publish mqtt message
     * @param topic topic
     * @param payload body
     * @param qos qos
     * @param isRetained should retain message
     */
    public void publish(String topic, String payload, int qos, boolean isRetained) {
        BaseClient publisher = null;
        try {
            publisher = publisherObjectPool.borrowObject();
            publisher.publish(topic, payload, qos, isRetained);
        } catch (Exception e) {
            logger.error("Error on borrow publisher from pool.", e);
        } finally {
            try {
                publisherObjectPool.returnObject(publisher);
            } catch (Exception e) {
                logger.error("While returning object to pool.", e);
            }
        }
    }

    /**
     * Clear all pool.
     * publisher client will call disconnect.
     */
    public void clearPool() {
        try {
            publisherObjectPool.clear();
        } catch (Exception e) {
            logger.error("While clear pool.", e);
        }
    }

    private static class PublisherPoolFactory extends BasePooledObjectFactory<BaseClient> {

        private final String brokerUrl;
        private final boolean isSync;

        PublisherPoolFactory(String brokerUrl, boolean isSync) {
            this.brokerUrl = brokerUrl;
            this.isSync = isSync;
        }

        @Override
        public BaseClient create() throws Exception {
            if (isSync) {
                return new PublisherSync(brokerUrl, UUID.randomUUID().toString());
            } else {
                return new PublisherAsync(brokerUrl, UUID.randomUUID().toString());
            }
        }

        @Override
        public PooledObject<BaseClient> wrap(BaseClient obj) {
            return new DefaultPooledObject<>(obj);
        }

        @Override
        public void destroyObject(PooledObject<BaseClient> p) throws Exception {
            p.getObject().disconnect();
        }
    }

}
