/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.hiveio.options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public abstract class BaseCmd implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(BaseCmd.class);

  @Inject public SocksProxyOptions socksOpts = new SocksProxyOptions();
  @Inject public MetricsOptions metricsOpts = new MetricsOptions();

  @Override public void run() {
    metricsOpts.process();
    if (socksOpts.port != -1) {
      System.setProperty("socksProxyHost", socksOpts.host);
      System.setProperty("socksProxyPort", Integer.toString(socksOpts.port));
    }
    try {
      execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public abstract void execute() throws Exception;
}
