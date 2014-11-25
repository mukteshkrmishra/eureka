/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.eureka2.server.transport.tcp;

import com.netflix.eureka2.registry.InstanceInfo;
import com.netflix.eureka2.server.EurekaServerConfig;
import com.netflix.eureka2.server.metric.EurekaServerMetricFactory;
import com.netflix.eureka2.server.registry.EurekaServerRegistry;
import io.reactivex.netty.metrics.MetricEventsListenerFactory;
import io.reactivex.netty.server.RxServer;

import javax.annotation.PreDestroy;

/**
 * @author Tomasz Bak
 */
public class AbstractTcpServer {

    protected final EurekaServerConfig config;
    protected final EurekaServerRegistry<InstanceInfo> eurekaRegistry;
    protected final MetricEventsListenerFactory servoEventsListenerFactory;
    protected final EurekaServerMetricFactory metricFactory;
    protected RxServer<Object, Object> server;

    public AbstractTcpServer(EurekaServerRegistry eurekaRegistry, MetricEventsListenerFactory servoEventsListenerFactory,
                             EurekaServerConfig config, EurekaServerMetricFactory metricFactory) {
        this.eurekaRegistry = eurekaRegistry;
        this.servoEventsListenerFactory = servoEventsListenerFactory;
        this.config = config;
        this.metricFactory = metricFactory;
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        if (server != null) {
            server.shutdown();
        }
    }
}