/**
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

package fm.last.feathers.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.mapred.*;
import org.apache.hadoop.typedbytes.TypedBytesWritable;

public class Sum extends MapReduceBase
  implements Reducer<TypedBytesWritable, TypedBytesWritable, 
                     TypedBytesWritable, TypedBytesWritable> {

  private final TypedBytesWritable sum = new TypedBytesWritable();
  
  public void reduce(TypedBytesWritable key, Iterator<TypedBytesWritable> values, 
                     OutputCollector<TypedBytesWritable, TypedBytesWritable> output,
                     Reporter reporter) throws IOException {
    int s = 0;
    while (values.hasNext()) {
      s += (Integer) values.next().getValue();
    }
    sum.setValue(s);
    output.collect(key, sum);
  }

}
