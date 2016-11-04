/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.helloworld.health;

import com.codahale.metrics.health.HealthCheck;
import java.util.List;

public class TemplateHealthCheck extends HealthCheck {
  private final List<String> templates;

  public TemplateHealthCheck(List<String> templates) {
    this.templates = templates;
  }

  @Override
  protected Result check() throws Exception {
    for (String template : templates) {
      final String saying = String.format(template, "TEST");
      if (!saying.contains("TEST")) {
        return Result.unhealthy("template doesn't include a name");
      }
    }
    return Result.healthy();
  }
}
