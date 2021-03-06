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

package org.apache.flink.statefun.e2e.exactlyonce;

import org.apache.flink.statefun.e2e.exactlyonce.generated.ExactlyOnceVerification.WrappedMessage;
import org.apache.flink.statefun.sdk.Context;
import org.apache.flink.statefun.sdk.FunctionType;
import org.apache.flink.statefun.sdk.StatefulFunction;

final class FnUnwrapper implements StatefulFunction {

  static final FunctionType TYPE =
      new FunctionType("org.apache.flink.e2e.exactlyonce", "unwrapper");

  @Override
  public void invoke(Context context, Object input) {
    final WrappedMessage message = requireWrappedMessage(input);
    context.send(FnCounter.TYPE, message.getInvokeTargetId(), message);
  }

  private static WrappedMessage requireWrappedMessage(Object input) {
    return (WrappedMessage) input;
  }
}
