#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
#!/bin/bash
SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"
#HADOOP_HOME='/mnt/vol/hive/sites/graph/hadoop'
HADOOP_HOME='/mnt/vol/hive/sites/silver/hadoop'


#HIVE_HOME='/mnt/vol/hive/sites/prism_frc/hive'
HIVE_HOME='/mnt/vol/hive/sites/silver/hive'
MAPRED_POOL_NAME='di.nonsla'
# Fill in the correct database (namespace) i.e. digraph, default_platinum, default_freon, etc.
HIVE_DATABASE_NAME='default'
HIVE_OPTS+=" -hiveconf mapred.fairscheduler.pool=$MAPRED_POOL_NAME"
hive_site_custom="$HIVE_HOME"/conf/hive-site-custom.xml
if [ -e $hive_site_custom ]
then
  HIVE_OPTS+=" -hiveconf tmpfiles=file://"$hive_site_custom""
fi

echo "HADOOP_HOME=$HADOOP_HOME"
echo "HIVE_HOME=$HIVE_HOME"
echo "HIVE_OPTS=$HIVE_OPTS"
echo "MAPRED_POOL_NAME=$MAPRED_POOL_NAME"

USE_DEPLOYED_CORONA_JAR=false
export HADOOP_CLASSPATH HADOOP_HOME HIVE_HOME HIVE_OPTS USE_DEPLOYED_CORONA_JAR
#jar="target/hive-io-exp-cmdline-0.7-SNAPSHOT.jar"
jar="${SCRIPT_DIR}/target/hive-io-exp-cmdline-0.7-SNAPSHOT-jar-with-dependencies.jar"
cmd="$HIVE_HOME/bin/hive --service jar $jar com.facebook.giraph.hive.cmdline.Main -Dmapred.fairscheduler.pool=$MAPRED_POOL_NAME "
echo $cmd
eval $cmd
#pid=$!
#echo "pid for hive $pid"
#kill -s STOP $pid
