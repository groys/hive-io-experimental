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
package com.facebook.giraph.hive.mapreduce;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SampleInputSplit extends InputSplit implements Writable {
  private int num;

  public SampleInputSplit() {
  }

  public SampleInputSplit(int num) {
    this.num = num;
  }

  public int getNum() {
    return num;
  }

  @Override public long getLength() throws IOException, InterruptedException {
    return 0;
  }

  @Override public String[] getLocations()
      throws IOException, InterruptedException {
    return new String[0];
  }

  @Override public void readFields(DataInput in) throws IOException {
    num = in.readInt();
  }

  @Override public void write(DataOutput out) throws IOException {
    out.writeInt(num);
  }
}
