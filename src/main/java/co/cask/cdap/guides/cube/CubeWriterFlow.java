/*
 * Copyright © 2015 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.cdap.guides.cube;

import co.cask.cdap.api.flow.Flow;
import co.cask.cdap.api.flow.FlowSpecification;

/**
 * Simple flow for parsing weblogs and writing them to a {@link co.cask.cdap.api.dataset.lib.cube.Cube}.
 */
public class CubeWriterFlow implements Flow {
  static final String FLOW_NAME = "CubeWriterFlow";

  @Override
  public FlowSpecification configure() {
    return FlowSpecification.Builder.with()
      .setName(FLOW_NAME)
      .setDescription("Reads logs from a Stream and writes them to a Cube dataset")
      .withFlowlets()
        .add("writer", new CubeWriterFlowlet())
      .connect()
        .fromStream(WebAnalyticsApp.STREAM_NAME).to("writer")
      .build();
  }
}
